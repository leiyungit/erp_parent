package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Goodstype;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
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
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet("商品");
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 写入表头
        // - 这个表头应在实体类上定义注解，标注导出的表头名称， 同时应该可以实现通用
        String[] headerName = {"编号","名称","产地","厂家","计量单位","进货价格","销售价格","商品类型"};
        // 指定每一列的宽度
        int[] columnWidths = {8*256,18*256,16*256,20*259,10*256,10*256,10*256,12*256};
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
            sheet.setColumnWidth(i,columnWidths[i]);
        }
        // 写入内容
        int index = 1;
        for (Goods goods : list) {
            row = sheet.createRow(index);
            row.createCell(0).setCellValue(goods.getUuid());
            row.createCell(1).setCellValue(goods.getName());
            row.createCell(2).setCellValue(goods.getOrigin());
            row.createCell(3).setCellValue(goods.getProducer());
            row.createCell(4).setCellValue(goods.getUnit());
            row.createCell(5).setCellValue(goods.getInprice());
            row.createCell(6).setCellValue(goods.getOutprice());
            row.createCell(7).setCellValue(goods.getGoodstype().getName());
            index++;

        }
        // 保存到本地目录
        try {
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != wk) {
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
            Map<String,Long> goodstypeMap = new HashMap<>();
            for (int i = 1; i <= lastRowNum; i++) {
                goods = new Goods();
                String name = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                goods.setName(name);
                List<Goods> list = this.goodsDao.getList(null,goods, null);
                if(list.size()>0){
                    goods = list.get(0);
                }
                goods.setOrigin(sheet.getRow(i).getCell(1).getStringCellValue());
                goods.setProducer(sheet.getRow(i).getCell(2).getStringCellValue());
                goods.setUnit(sheet.getRow(i).getCell(3).getStringCellValue());
                sheet.getRow(i).getCell(4).setCellType(CellType.NUMERIC);
                sheet.getRow(i).getCell(5).setCellType(CellType.NUMERIC);
                goods.setInprice(sheet.getRow(i).getCell(4).getNumericCellValue());
                goods.setOutprice(sheet.getRow(i).getCell(5).getNumericCellValue());
                String typeName = sheet.getRow(i).getCell(6).getStringCellValue().trim();
                if(null == goods.getGoodstype()){
                    Goodstype goodstype = new Goodstype();
                    Long goodstypeCode = this.goodstypeBiz.getGoodstypeCode(typeName, goodstypeMap);
                    if(null == goodstypeCode){
                        throw new ErpException("商品类型未定义");
                    }
                    goodstype.setUuid(goodstypeCode);
                    goodstype.setName(typeName);
                    goods.setGoodstype(goodstype);
                }else{
                    goods.getGoodstype().setName(typeName);
                }

                if(list.size() == 0){
                    this.goodsDao.add(goods);
                }
            }
        }  finally {
            if(null != book)
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String getGoodsName(Long id, Map<Long,String> goodsMap){
        if(null == id){
            return null;
        }
        if(!goodsMap.containsKey(id)){
            String name = this.goodsDao.get(id).getName();
            goodsMap.put(id,name);
        }
        return goodsMap.get(id);
    }
}
