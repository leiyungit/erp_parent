package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Supplier;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface ISupplierBiz extends IBaseBiz<Supplier>{

    /**
     * 导出到excel文件
     * @param os 输出流
     * @param t1 查询条件
     */
	void export(OutputStream os,Supplier t1) throws IOException;
}

