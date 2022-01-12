package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Store;

import java.util.Map;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IStoreBiz extends IBaseBiz<Store>{

    /**
     * 根据仓库编号获取仓库名称，并存map
     * @param id
     * @param storeMap
     * @return
     */
    String getStoreName(Long id, Map<Long,String> storeMap);
}

