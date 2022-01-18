package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.dao.IGoodstypeDao;
import cn.itcast.erp.entity.Goodstype;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class GoodstypeBiz extends BaseBiz<Goodstype> implements IGoodstypeBiz {

	private IGoodstypeDao goodstypeDao;
	
	public void setGoodstypeDao(IGoodstypeDao goodstypeDao) {
		this.goodstypeDao = goodstypeDao;
		setBaseDao(goodstypeDao);
	}


    /**
     * 根据类别名称获取类别编号
     *
     * @param name
     * @param goodstypeMap
     * @return
     */
    @Override
    public Long getGoodstypeCode(String name, Map<String,Long> goodstypeMap) {
        if(null == name){
            return null;
        }
        if(!goodstypeMap.containsKey(name)){
            Goodstype goodstype = new Goodstype();
            goodstype.setName(name);
            List<Goodstype> list = this.goodstypeDao.getList(null, goodstype, null);
            if(list.size()>0){
                goodstypeMap.put(name,list.get(0).getUuid());
            }
        }
        //System.out.println("商品类别："+goodstypeMap);
        return goodstypeMap.get(name);
    }

    /**
     * 根据类别编号获取类别名称
     *
     * @param id
     * @param goodstypeMap
     * @return
     */
    @Override
    public String getGoodstypeName(Long id, Map<Long, String> goodstypeMap) {
        if(null == id){
            return null;
        }
        if(!goodstypeMap.containsKey(id)){
            String name = this.goodstypeDao.get(id).getName();
            goodstypeMap.put(id,name);
        }
        return goodstypeMap.get(id);
    }
}
