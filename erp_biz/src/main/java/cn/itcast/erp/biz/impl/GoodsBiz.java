package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Goodstype;
import freemarker.template.utility.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 *
 * @author Administrator
 */
public class GoodsBiz extends BaseBiz<Goods> implements IGoodsBiz {

    private IGoodsDao goodsDao;
    private IGoodstypeBiz goodstypeBiz;

    public void setGoodstypeBiz(IGoodstypeBiz goodstypeBiz) {
        this.goodstypeBiz = goodstypeBiz;
    }

    public void setGoodsDao(IGoodsDao goodsDao) {
        this.goodsDao = goodsDao;
        setBaseDao(goodsDao);
    }

    /**
     * 导出到excel文件
     *
     * @param os 输出流
     * @param t1 查询条件
     */
    @Override
    public void export(OutputStream os, Goods t1) throws IOException {
        System.out.println(t1);
        List<Goods> list = this.goodsDao.getList(t1, null, null);
        // 创建excel工作薄
        HSSFWorkbook wk = new HSSFWorkbook();
        try {
            // 创建一张工作表
            HSSFSheet sheet = wk.createSheet("商品");
            // 创建第一行
            HSSFRow row = sheet.createRow(0);
            // 写入表头
            // - 这个表头应在实体类上定义注解，标注导出的表头名称， 同时应该可以实现通用
            // String[] headerName = {"编号","名称","产地","厂家","计量单位","进货价格","销售价格","商品类型"};
            String[] headerName = {"编号", "名称", "规格", "型号", "颜色", "计量单位", "进货价格", "销售价格", "期初库存", "最低安全库存", "最高安全库存", "保质期(天)", "产地", "厂家", "商品类型"};
            // 指定每一列的宽度
            int[] columnWidths = {8 * 256, 18 * 256, 16 * 256, 16 * 256, 8 * 256, 10 * 256, 10 * 256, 10 * 256, 10 * 256, 12 * 256, 12 * 256, 12 * 256, 16 * 259, 20 * 259, 12 * 256};
            // 向单元格写值
            HSSFCell cell = null;
            HSSFCellStyle style_title = wk.createCellStyle();
            HSSFFont font_title = wk.createFont();
            font_title.setBold(true);
            font_title.setFontName("黑体");
            style_title.setFont(font_title);
            for (int i = 0; i < headerName.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headerName[i]);
                cell.setCellStyle(style_title);
                sheet.setColumnWidth(i, columnWidths[i]);
            }
            // 写入内容
            int index = 1;
            for (Goods goods : list) {
                row = sheet.createRow(index);
                row.createCell(0).setCellValue(goods.getUuid());
                row.createCell(1).setCellValue(goods.getName());
                if (null != goods.getSpec())
                    row.createCell(2).setCellValue(goods.getSpec());
                if (null != goods.getModel())
                    row.createCell(3).setCellValue(goods.getModel());
                if (null != goods.getColour())
                    row.createCell(4).setCellValue(goods.getColour());
                row.createCell(5).setCellValue(goods.getUnit());
                row.createCell(6).setCellValue(goods.getInprice());
                row.createCell(7).setCellValue(goods.getOutprice());
                if (null != goods.getBeginstorenum())
                    row.createCell(8).setCellValue(goods.getBeginstorenum());
                if (null != goods.getMinsafenum())
                    row.createCell(9).setCellValue(goods.getMinsafenum());
                if (null != goods.getMaxsafenum())
                    row.createCell(10).setCellValue(goods.getMaxsafenum());
                if (null != goods.getShelflife())
                    row.createCell(11).setCellValue(goods.getShelflife());
                if (null != goods.getOrigin())
                    row.createCell(12).setCellValue(goods.getOrigin());
                if (null != goods.getProducer())
                    row.createCell(13).setCellValue(goods.getProducer());
                if (null != goods.getGoodstype())
                    row.createCell(14).setCellValue(goods.getGoodstype().getName());
                index++;

            }
            // 保存到本地目录

            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != wk) {
                try {
                    wk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * excel导入
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void doImport(InputStream is) throws IOException {
        HSSFWorkbook book = null;
        try {
            book = new HSSFWorkbook(is);
            HSSFSheet sheet = book.getSheetAt(0);
            // 最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            Goods goods = null;
            Map<String, Long> goodstypeMap = new HashMap<>();
            for (int i = 1; i <= lastRowNum; i++) {
                goods = new Goods();
                String name = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                goods.setName(name);
                List<Goods> list = this.goodsDao.getList(null, goods, null);
                if (list.size() > 0) {
                    goods = list.get(0);
                }
                //{"规格","型号","颜色","计量单位","进货价格","销售价格" ,"期初库存","最低安全库存","最高安全库存","保质期(天)","产地","厂家","商品类型"};
                goods.setSpec(this.getSheetCellValue(sheet,i,1));
                goods.setModel(this.getSheetCellValue(sheet,i,2));
                goods.setColour(this.getSheetCellValue(sheet,i,3));
                goods.setUnit(this.getSheetCellValue(sheet,i,4));
                for (int ce = 5; ce < 11; ce++) {
                    this.setSheetCellTypeNumeric(sheet,i,ce);
                }
                goods.setInprice(this.getSheetCellNumericValue(sheet,i,5));
                goods.setOutprice(this.getSheetCellNumericValue(sheet,i,6));
                goods.setBeginstorenum(this.getSheetCellNumericValue(sheet,i,7));
                goods.setMinsafenum(this.getSheetCellNumericValue(sheet,i,8));
                goods.setMaxsafenum(this.getSheetCellNumericValue(sheet,i,9));
                Double shelflife = this.getSheetCellNumericValue(sheet, i, 10);
                if(shelflife != null)
                    goods.setShelflife(Integer.valueOf(shelflife+""));
                goods.setOrigin(this.getSheetCellValue(sheet,i,11));
                goods.setProducer(this.getSheetCellValue(sheet,i,12));
                String typeName = this.getSheetCellValue(sheet,i,13);
                if(StringUtils.isEmpty(typeName)){
                    throw new ErpException("商品类型不可以为空");
                }
                if (null == goods.getGoodstype()) {
                    Goodstype goodstype = new Goodstype();
                    Long goodstypeCode = this.goodstypeBiz.getGoodstypeCode(typeName, goodstypeMap);
                    if (null == goodstypeCode) {
                        throw new ErpException("商品类型未定义");
                    }
                    goodstype.setUuid(goodstypeCode);
                    goodstype.setName(typeName);
                    goods.setGoodstype(goodstype);
                } else {
                    goods.getGoodstype().setName(typeName);
                }
                if (list.size() == 0) {
                    this.goodsDao.add(goods);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if (null != book)
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private String getSheetCellValue(HSSFSheet sheet, Integer rowIndex, Integer cellIndex){
        if(null == sheet){
            return null;
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellIndex);
        if(null != cell){
            return cell.getStringCellValue();
        }
        return null;
    }

    private void setSheetCellTypeNumeric(HSSFSheet sheet, Integer rowIndex, Integer cellIndex){
        if(null == sheet){
            return;
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellIndex);
        if(null != cell){
            cell.setCellType(CellType.NUMERIC);
        }
    }

    private Double getSheetCellNumericValue(HSSFSheet sheet, Integer rowIndex, Integer cellIndex){
        if(null == sheet){
            return null;
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellIndex);
        if(null != cell){
            return cell.getNumericCellValue();
        }
        return null;
    }

    public String getGoodsName(Long id, Map<Long, String> goodsMap) {
        if (null == id) {
            return null;
        }
        if (!goodsMap.containsKey(id)) {
            String name = this.goodsDao.get(id).getName();
            goodsMap.put(id, name);
        }
        return goodsMap.get(id);
    }
}
