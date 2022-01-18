package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Supplier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

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

    /**
     * excel导入
     * @param is
     * @throws IOException
     */
	void doImport(InputStream is)throws IOException;

    /**
     * 根据供应商/客户编号获取名称
     * @param SupplierUuid
     * @param SupplierMap 缓存容器
     * @return
     */
    String getSupplierName(Long SupplierUuid, Map<Long,String> SupplierMap);
}

