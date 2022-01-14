package cn.itcast.erp.dao.impl;
import cn.itcast.erp.entity.Storealert;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IStoredetailDao;
import cn.itcast.erp.entity.Storedetail;

import java.util.List;

/**
 * 数据访问类
 * @author Administrator
 *
 */
public class StoredetailDao extends BaseDao<Storedetail> implements IStoredetailDao {

	
	/**
	 * 构建查询条件
	 * @param storedetail1
	 * @param storedetail2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Storedetail storedetail1,Storedetail storedetail2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Storedetail.class);
		if(storedetail1!=null){
            // 根据仓库编码查询
            if(storedetail1.getStoreuuid()!= null){
                dc.add(Restrictions.eq("storeuuid", storedetail1.getStoreuuid()));
            }
            // 根据商品编码查询
            if(storedetail1.getGoodsuuid()!= null){
                dc.add(Restrictions.eq("goodsuuid", storedetail1.getGoodsuuid()));
            }
		}		
		return dc;
	}


    /**
     * 获取库存预警列表
     *
     * @return
     */
    @Override
    public List<Storealert> getStorealertList() {
        return (List<Storealert>) getHibernateTemplate().find("from Storealert where storenum<outnum-innum");
    }
}

