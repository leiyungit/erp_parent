package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IStoreBiz;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.entity.Store;

import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class StoreBiz extends BaseBiz<Store> implements IStoreBiz {

	private IStoreDao storeDao;
	
	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		setBaseDao(storeDao);
	}

    public String getStoreName(Long id, Map<Long,String> storeMap){
        if(null == id){
            return null;
        }
        if(!storeMap.containsKey(id)){
            String name = this.storeDao.get(id).getName();
            storeMap.put(id,name);
        }
        return storeMap.get(id);
    }
}
