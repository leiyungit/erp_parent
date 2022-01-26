package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.biz.enums.OrderDetailStateEnum;
import cn.itcast.erp.biz.enums.OrdersStateEnum;
import cn.itcast.erp.biz.enums.OrdersTypeEnum;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;
import cn.itcast.redsun.ws.Waybilldetail;
import cn.itcast.redsun.ws.impl.IWaybillWs;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 *
 * @author Administrator
 */
public class OrdersBiz extends BaseBiz<Orders> implements IOrdersBiz {

    private IOrdersDao ordersDao;
    private IEmpBiz empBiz;
    private ISupplierBiz supplierBiz;
    /** webService*/
    private IWaybillWs waybillWs;

    public void setWaybillWs(IWaybillWs waybillWs) {
        this.waybillWs = waybillWs;
    }

    public void setOrdersDao(IOrdersDao ordersDao) {
        this.ordersDao = ordersDao;
        setBaseDao(ordersDao);
    }

    public void setEmpBiz(IEmpBiz empBiz) {
        this.empBiz = empBiz;
    }

    public void setSupplierBiz(ISupplierBiz supplierBiz) {
        this.supplierBiz = supplierBiz;
    }

    @Override
    public List<Orders> listByPage(Orders t1, Orders t2, Object param, int firstResult, int maxResults) {
        System.out.println("分页查询订单主表");
        List<Orders> orders = this.ordersDao.listByPage(t1, t2, param, firstResult, maxResults);
        // 缓存员工名称
        Map<Long, String> empMap = new HashMap<>();
        // 缓存供应商名称
        Map<Long, String> supplierMap = new HashMap<>();
        //Long start = System.currentTimeMillis();
        orders.stream().forEach(e -> {
            e.setCreaterName(this.empBiz.getEmpName(e.getCreater(), empMap));
            e.setCheckerName(this.empBiz.getEmpName(e.getChecker(), empMap));
            e.setStarterName(this.empBiz.getEmpName(e.getStarter(), empMap));
            e.setEnderName(this.empBiz.getEmpName(e.getEnder(), empMap));
            e.setSupplierName(this.supplierBiz.getSupplierName(e.getSupplieruuid(), supplierMap));  // 设置供应商名称
        });
        //Long end = System.currentTimeMillis();
        //System.out.println("A方案耗时："+(end-start));
       /* Long start = System.currentTimeMillis();
        orders.stream().map(e->{
            e.setCreaterName(this.getEmpName(e.getCreater(),empMap));
            e.setCheckerName(this.getEmpName(e.getChecker(),empMap));
            e.setStarterName(this.getEmpName(e.getStarter(),empMap));
            e.setEnderName(this.getEmpName(e.getEnder(),empMap));
            e.setSupplierName(this.getSupplierName(e.getSupplieruuid(),supplierMap));
            return e;
        }).collect(Collectors.toList());
        Long end = System.currentTimeMillis();
        System.out.println("B方案耗时："+(end-start));*/
        return orders;
    }

    @Override
    public void add(Orders orders) {
        // 新增采购订单状态
        orders.setState(OrdersStateEnum.NEW.getCode());
        // 采购/销售， 类型由前端传入
        // orders.setType(OrdersTypeEnum.PO.getCode());
        // 订单创建时间
        orders.setCreatetime(new Date());
        // 采购总金额
        double total = 0;
        double money = 0;
        Goods goods = null;
        for (Orderdetail orderDetail : orders.getOrderDetails()) {
            // 累计金额, 前端商品单价是可以修改的
            total += orderDetail.getMoney();
            // 获取商品信息中的采购单价，避免被篡改url提交的数据
            /*goods = goodsDao.get(orderDetail.getGoodsuuid());
            if(goods == null){
                throw new ERPException("订单关联的商品已失效");
            }
            money = DoubleUtil.mul(goods.getInprice(), Double.valueOf(orderDetail.getNum()));
            */
            //money = DoubleUtil.mul(orderDetail.getPrice(), Double.valueOf(orderDetail.getNum()));
            //total = DoubleUtil.add(total,money);
            // System.out.println(orderDetail.getGoodsname() + "=》数量："+Double.valueOf(orderDetail.getNum()) + "，小计："+money + "，   合计："+total);
            // 明细单状态
            orderDetail.setState(OrderDetailStateEnum.PO_NOT_IN.getCode());
            // 设置明细对应的订单。原因：orders采用级联更新，且外键的维护交给明细来维护
            orderDetail.setOrders(orders);
        }
        // 设置总金额
        orders.setTotalmoney(total);
        System.out.print("业务层，新增订单：");
        System.out.println(orders);
        // System.out.println(orders.getOrderDetails());
        this.ordersDao.add(orders);
    }

    /**
     * 采购订单审核
     *
     * @param uuid
     * @param empUuid
     */
    @Override
    public void doCheck(Long uuid, Long empUuid) {
        Orders orders = this.ordersDao.get(uuid);
        if (orders.getState() != OrdersStateEnum.NEW.getCode()) {
            throw new ErpException("该订单已审核过!");
        }
        orders.setChecker(empUuid);
        orders.setChecktime(new Date());
        orders.setState(OrdersStateEnum.CHECK.getCode());
        // this.ordersDao.update(orders);
    }

    /**
     * 采购订单确认
     *
     * @param uuid
     * @param empUuid
     */
    @Override
    public void doStart(Long uuid, Long empUuid) {
        Orders orders = this.ordersDao.get(uuid);
        if (orders.getState() != OrdersStateEnum.CHECK.getCode()) {
            throw new ErpException("该订单已确认过!");
        }
        orders.setStarter(empUuid);
        orders.setStarttime(new Date());
        orders.setState(OrdersStateEnum.START.getCode());
    }

    /**
     * 导出订单为excel文件
     *
     * @param os
     * @param uuid 订单编号
     */
    @Override
    public void exportById(OutputStream os, Long uuid) {
        Orders orders = this.ordersDao.get(uuid);
        // 创建一张工作表
        String sheetName = "";
        if (OrdersTypeEnum.PO.getCode() == orders.getType()) {
            sheetName = "采 购 单";
        } else if (OrdersTypeEnum.SO.getCode() == orders.getType()) {
            sheetName = "销 售 单";
        }
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet(sheetName);
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 创建第一行中的第一个单元格
        HSSFCell cell = row.createCell(0);
        // 向单元格写值
        cell.setCellValue(sheetName);
        // 设置单元格样式
        HSSFCellStyle style_content = wk.createCellStyle();
        style_content.setBorderTop(BorderStyle.THIN); // 上边线
        style_content.setBorderRight(BorderStyle.THIN); // 边线
        style_content.setBorderBottom(BorderStyle.THIN); // 边线
        style_content.setBorderLeft(BorderStyle.THIN); // 边线
        // 对齐方式
        style_content.setAlignment(HorizontalAlignment.CENTER);
        style_content.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置标题
        HSSFCellStyle style_title = wk.createCellStyle();
        style_title.setAlignment(HorizontalAlignment.CENTER);
        style_title.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体样式
        // 标题样式
        HSSFFont font_title = wk.createFont();
        font_title.setBold(true);
        font_title.setFontName("黑体");
        font_title.setFontHeightInPoints((short) 18);
        style_title.setFont(font_title);
        HSSFRow rowTitle = sheet.getRow(0);
        rowTitle.getCell(0).setCellStyle(style_title);
        rowTitle.setHeight((short) 1000);
        // 内容字体样式
        HSSFFont font_content = wk.createFont();
        font_content.setFontName("宋体");
        font_content.setFontHeightInPoints((short) 11);
        style_content.setFont(font_content);
        // 设置内容部分列宽
        for (int i = 0; i < 4; i++) {
            sheet.setColumnWidth(i, 5000);
        }

        int dataSize = orders.getOrderDetails().size();
        int rowCount = 9 + dataSize;
        for (int i = 2; i <= rowCount; i++) {
            System.out.println("修改行："+(i+1));
            row = sheet.createRow(i);
            row.setHeight((short) 500); //  设置内容部分行高
            for (int j = 0; j < 4; j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style_content);
            }
        }

        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));
        // 添加订单主表title
        sheet.getRow(2).getCell(0).setCellValue("供应商");
        sheet.getRow(3).getCell(0).setCellValue("单据日期");
        sheet.getRow(4).getCell(0).setCellValue("审核日期");
        sheet.getRow(5).getCell(0).setCellValue("采购日期");
        sheet.getRow(6).getCell(0).setCellValue("入库日期");
        sheet.getRow(3).getCell(2).setCellValue("经办人");
        sheet.getRow(4).getCell(2).setCellValue("经办人");
        sheet.getRow(5).getCell(2).setCellValue("经办人");
        sheet.getRow(6).getCell(2).setCellValue("经办人");
        sheet.getRow(7).getCell(0).setCellValue("订单明细");
        sheet.getRow(8).getCell(0).setCellValue("商品名称");
        sheet.getRow(8).getCell(1).setCellValue("数量");
        sheet.getRow(8).getCell(2).setCellValue("价格");
        sheet.getRow(8).getCell(3).setCellValue("金额");

        // 数据库数据写入文档
        if (null != orders.getNotedate())
            sheet.getRow(3).getCell(1).setCellValue(orders.getNotedate());
        if (null != orders.getChecktime())
            sheet.getRow(4).getCell(1).setCellValue(orders.getChecktime());
        if (null != orders.getStarttime())
            sheet.getRow(5).getCell(1).setCellValue(orders.getStarttime());
        if (null != orders.getEndtime())
            sheet.getRow(6).getCell(1).setCellValue(orders.getEndtime());
        // 缓存员工名称
        Map<Long, String> empMap = new HashMap<>();
        // 缓存供应商名称
        Map<Long, String> supplierMap = new HashMap<>();
        if (null != orders.getSupplieruuid())
            sheet.getRow(2).getCell(1).setCellValue(this.supplierBiz.getSupplierName(orders.getSupplieruuid(), supplierMap));
        if (null != orders.getCreater())
            sheet.getRow(3).getCell(3).setCellValue(empBiz.getEmpName(orders.getCreater(), empMap));
        if (null != orders.getChecker())
            sheet.getRow(4).getCell(3).setCellValue(empBiz.getEmpName(orders.getChecker(), empMap));
        if (null != orders.getStarter())
            sheet.getRow(5).getCell(3).setCellValue(empBiz.getEmpName(orders.getStarter(), empMap));
        if (null != orders.getEnder())
            sheet.getRow(6).getCell(3).setCellValue(empBiz.getEmpName(orders.getEnder(), empMap));
        // 日期格式
        HSSFCellStyle data_styel = wk.createCellStyle();
        data_styel.cloneStyleFrom(style_content); // 复制
        HSSFDataFormat dataFormat = wk.createDataFormat();
        data_styel.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm"));
        for (int i = 3; i < 7; i++) {
            sheet.getRow(i).getCell(1).setCellStyle(data_styel);
        }
        // 列表数据写入
        List<Orderdetail> orderDetails = orders.getOrderDetails();
        for (int i = 9; i < rowCount; i++) {
            Orderdetail orderdetail = orderDetails.get(i - 9);
            row = sheet.getRow(i);
            row.getCell(0).setCellValue(orderdetail.getGoodsname());
            row.getCell(1).setCellValue(orderdetail.getPrice());
            row.getCell(2).setCellValue(orderdetail.getNum());
            row.getCell(3).setCellValue(orderdetail.getMoney());
        }
        row = sheet.getRow(rowCount);
        row.getCell(0).setCellValue("合计");
        row.getCell(3).setCellValue(orders.getTotalmoney());
        // 保存到本地目录
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == wk) {
                try {
                    wk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据运单号查询运单详情
     *
     * @param sn
     * @return
     */
    @Override
    public List<Waybilldetail> waybilldetailList(Long sn) {
        return waybillWs.waybilldetailList(sn);
    }
}
