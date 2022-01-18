package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.biz.enums.SupplierTypeEnum;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Supplier;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class SupplierBiz extends BaseBiz<Supplier> implements ISupplierBiz {

	private ISupplierDao supplierDao;
	
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		setBaseDao(supplierDao);
	}

    /**
     * 根据供应商/客户编号获取名称
     * @param SupplierUuid
     * @param SupplierMap 缓存容器
     * @return
     */
    public String getSupplierName(Long SupplierUuid, Map<Long,String> SupplierMap){
        if(SupplierUuid == null){
            return null;
        }
        if(!SupplierMap.containsKey(SupplierUuid)){
            String name = this.supplierDao.get(SupplierUuid).getName();
            SupplierMap.put(SupplierUuid, name);
            // System.out.println("供应商名称："+name);
            return name;
        }
        return SupplierMap.get(SupplierUuid);
    }

	/**
	 * 导出到excel文件
	 *
	 * @param os 输出流
	 * @param t1 查询条件
	 */
	@Override
	public void export(OutputStream os, Supplier t1) {

		System.out.println(t1);
		List<Supplier> list = this.supplierDao.getList(t1, null, null);
		// 创建excel工作薄
		HSSFWorkbook wk = new HSSFWorkbook();
		// 创建一张工作表
		HSSFSheet sheet = null;
		if(SupplierTypeEnum.SUPPLIER.getCode() == Integer.valueOf(t1.getType())){
			sheet = wk.createSheet("供应商");
		}else if(SupplierTypeEnum.CUSTOMER.getCode() == Integer.valueOf(t1.getType())){
			sheet = wk.createSheet("客户");
		}else{
			throw new ErpException("单据类型不匹配");
		}
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		// 写入表头
		// - 这个表头应在实体类上定义注解，标注导出的表头名称， 同时应该可以实现通用
		String[] headerName = {"名称","联系地址","联系人","联系电话","邮件地址"};
		// 指定每一列的宽度
		int[] columnWidths = {18*256,25*256,10*259,13*256,15*256};
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
		for (Supplier supplier : list) {
			row = sheet.createRow(index);
			row.createCell(0).setCellValue(supplier.getName());
			row.createCell(1).setCellValue(supplier.getAddress());
			row.createCell(2).setCellValue(supplier.getContact());
			row.createCell(3).setCellValue(supplier.getTele());
			row.createCell(4).setCellValue(supplier.getEmail());
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
            Integer type;
            if("供应商".equals(sheet.getSheetName())){
                type = SupplierTypeEnum.SUPPLIER.getCode();
            }else if("客户".equals(sheet.getSheetName())){
                type = SupplierTypeEnum.CUSTOMER.getCode();
            }else{
                throw new ErpException("工作表名称不正确");
            }
            // 最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            Supplier supplier = null;
            for (int i = 1; i <= lastRowNum; i++) {
                supplier = new Supplier();
                String name = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                supplier.setName(name);
                List<Supplier> list = this.supplierDao.getList(null,supplier, null);
                if(list.size()>0){
                    supplier = list.get(0);
                }
                supplier.setAddress(sheet.getRow(i).getCell(1).getStringCellValue());
                supplier.setContact(sheet.getRow(i).getCell(2).getStringCellValue());
                sheet.getRow(i).getCell(3).setCellType(CellType.STRING);
                supplier.setTele(sheet.getRow(i).getCell(3).getStringCellValue());
                supplier.setEmail(sheet.getRow(i).getCell(4).getStringCellValue());
                if(list.size() == 0){
                    supplier.setType(String.valueOf(type));
                    this.supplierDao.add(supplier);
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
}
