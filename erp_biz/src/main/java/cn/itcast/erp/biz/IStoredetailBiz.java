package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;

import java.util.List;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IStoredetailBiz extends IBaseBiz<Storedetail>{

    /**
     * 获取库存预警列表
     * @return
     */
    List<Storealert> getStorealertList();
}

