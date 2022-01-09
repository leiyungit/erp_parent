package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.dao.IStoredetailDao;
import cn.itcast.erp.entity.Store;
import cn.itcast.erp.entity.Storedetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class StoredetailBiz extends BaseBiz<Storedetail> implements IStoredetailBiz {

	private IStoredetailDao storedetailDao;
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;

	
	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
		setBaseDao(storedetailDao);
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
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
	public List<Storedetail> listByPage(Storedetail t1, Storedetail t2, Object param, int firstResult, int maxResults) {

		List<Storedetail> list = this.storedetailDao.listByPage(t1, t2, param, firstResult, maxResults);

		Map<Long,String> storeMap = new HashMap<>();
		Map<Long,String> goodsMap = new HashMap<>();

		list.stream().map(e->{
			e.setStoreName(this.getStoreName(e.getStoreuuid(),storeMap));
			e.setGoodsName(this.getGoodsName(e.getGoodsuuid(),goodsMap));
			return e;
		}).collect(Collectors.toList());

		return list;
	}

	private String getStoreName(Long id,Map<Long,String> storeMap){
		if(null == id){
			return null;
		}
		if(!storeMap.containsKey(id)){
			String name = this.storeDao.get(id).getName();
			storeMap.put(id,name);
		}
		return storeMap.get(id);
	}


	private String getGoodsName(Long id,Map<Long,String> goodsMap){
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
