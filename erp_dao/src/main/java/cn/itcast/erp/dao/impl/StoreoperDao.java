package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IStoreoperDao;
import cn.itcast.erp.entity.Storeoper;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class StoreoperDao extends BaseDao<Storeoper> implements IStoreoperDao {

	
	/**
	 * 构建查询条件
	 * @param storeoper1
	 * @param storeoper2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Storeoper storeoper1,Storeoper storeoper2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Storeoper.class);
		if(storeoper1!=null){
			if(storeoper1.getType()!=null )
			{
				dc.add(Restrictions.eq("type", storeoper1.getType()));
			}
			// 根据仓库编码查询
		    if(storeoper1.getStoreuuid()!= null){
                dc.add(Restrictions.eq("storeuuid", storeoper1.getStoreuuid()));
            }
		    // 根据商品编码查询
            if(storeoper1.getGoodsuuid()!= null){
                dc.add(Restrictions.eq("goodsuuid", storeoper1.getGoodsuuid()));
            }
		}		
		return dc;
	}
	
	
}

