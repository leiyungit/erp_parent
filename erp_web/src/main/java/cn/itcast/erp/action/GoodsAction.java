package cn.itcast.erp.action;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.biz.enums.SupplierTypeEnum;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.utils.ResultUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Action 
 * @author Administrator
 *
 */
public class GoodsAction extends BaseAction<Goods> {

	private IGoodsBiz goodsBiz;
	
	public void setGoodsBiz(IGoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		setBaseBiz(goodsBiz);
	}

    public void export() {
        String filename = "商品.xls";
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.getBytes(),
                            "ISO-8859-1")); // 中文名称转换
            this.goodsBiz.export(response.getOutputStream(), getT1());
        } catch (IOException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        }
    }

    private File file; // 上传文件
    private String fileFileName; // 文件名
    private String fileContentType; // 文件类型

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * 导入数据
     */
    public void doImport(){

        if(!"application/vnd.ms-excel".equals(fileContentType)){
            ResultUtil.ajaxReturnFail("上传的文件必须为excel");
            return;
        }
        try {
            this.goodsBiz.doImport(new FileInputStream(file));
            ResultUtil.ajaxReturnSuccess("上传文件成功");
        } catch (ErpException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            ResultUtil.ajaxReturnFail(e.getMessage());
        }

    }
}
