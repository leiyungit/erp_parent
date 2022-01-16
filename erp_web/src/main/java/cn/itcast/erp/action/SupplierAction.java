package cn.itcast.erp.action;

import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.biz.enums.SupplierTypeEnum;
import cn.itcast.erp.entity.Supplier;
import cn.itcast.erp.utils.ResultUtil;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Action
 *
 * @author Administrator
 */
public class SupplierAction extends BaseAction<Supplier> {

    private ISupplierBiz supplierBiz;

    public void setSupplierBiz(ISupplierBiz supplierBiz) {
        this.supplierBiz = supplierBiz;
        setBaseBiz(supplierBiz);
    }

    /**
     * remote 传过来的参数名称
     */
    private String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public void list() {
        if (null == getT1()) {
            setT1(new Supplier());
        }
        getT1().setName(q);
        super.list();
    }

    public void export() {
        String filename = "";
        System.out.println(SupplierTypeEnum.SUPPLIER.getCode());
        System.out.println(getT1().getType());
        if (SupplierTypeEnum.SUPPLIER.getCode() == Integer.valueOf(getT1().getType())) {
            filename = "供应商.xls";
        } else if (SupplierTypeEnum.CUSTOMER.getCode() == Integer.valueOf(getT1().getType())) {
            filename = "客户.xls";
        } else {
            ResultUtil.ajaxReturnFail("导出失败，单据类型不匹配");
            return;
        }
        HttpServletResponse response = ServletActionContext.getResponse();

        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.getBytes(),
                            "ISO-8859-1")); // 中文名称转换

            this.supplierBiz.export(response.getOutputStream(), getT1());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
