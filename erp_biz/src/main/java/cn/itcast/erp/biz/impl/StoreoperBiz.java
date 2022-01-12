package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.biz.IStoreBiz;
import cn.itcast.erp.biz.IStoreoperBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.dao.IStoreoperDao;
import cn.itcast.erp.entity.Storeoper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class StoreoperBiz extends BaseBiz<Storeoper> implements IStoreoperBiz {

	private IStoreoperDao storeoperDao;
	private IEmpBiz empBiz;
	private IStoreBiz storeBiz;
	private IGoodsBiz goodsBiz;
	
	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
		setBaseDao(storeoperDao);
	}

    public void setEmpBiz(IEmpBiz empBiz) {
        this.empBiz = empBiz;
    }

    public void setStoreBiz(IStoreBiz storeBiz) {
        this.storeBiz = storeBiz;
    }

    public void setGoodsBiz(IGoodsBiz goodsBiz) {
        this.goodsBiz = goodsBiz;
    }

    /**
     * 条件查询 分页
     *
     * @param t1
     * @param t2
     * @param param
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<Storeoper> listByPage(Storeoper t1, Storeoper t2, Object param, int firstResult, int maxResults) {
        List<Storeoper> list = super.listByPage(t1, t2, param, firstResult, maxResults);
        // 缓存
        Map<Long,String> storeMap = new HashMap<>();
        Map<Long,String> goodsMap = new HashMap<>();
        Map<Long,String> empMap = new HashMap<>();
        list.stream().forEach(e->{
            e.setEmpName(empBiz.getEmpName(e.getEmpuuid(),empMap));
            e.setStoreName(storeBiz.getStoreName(e.getStoreuuid(),storeMap));
            e.setGoodsName(goodsBiz.getGoodsName(e.getGoodsuuid(),goodsMap));
        });
        return list;
    }
}
