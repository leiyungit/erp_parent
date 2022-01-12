package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;

import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class GoodsBiz extends BaseBiz<Goods> implements IGoodsBiz {

	private IGoodsDao goodsDao;
	
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		setBaseDao(goodsDao);
	}

    public String getGoodsName(Long id, Map<Long,String> goodsMap){
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
