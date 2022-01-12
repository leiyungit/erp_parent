package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IStoreoperDao;
import cn.itcast.erp.entity.Storeoper;

import java.util.Calendar;

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
        Calendar car = Calendar.getInstance();
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
            }// 操作员编码查询
            if(storeoper1.getEmpuuid()!= null){
                dc.add(Restrictions.eq("empuuid", storeoper1.getEmpuuid()));
            }
            // 操作起始时间
            if(storeoper1.getOpertime() != null){
                car.setTime(storeoper1.getOpertime());
                car.set(Calendar.HOUR,0);
                car.set(Calendar.MINUTE,0);
                car.set(Calendar.SECOND,0);
                car.set(Calendar.MILLISECOND,0);
                dc.add(Restrictions.ge("opertime",car.getTime()));
            }
		}

        if(null != storeoper2){
            // 操作结束时间
            if(storeoper2.getOpertime() != null){
                car.setTime(storeoper2.getOpertime());
                car.set(Calendar.HOUR,23);
                car.set(Calendar.MINUTE,59);
                car.set(Calendar.SECOND,59);
                car.set(Calendar.MILLISECOND,999);
                dc.add(Restrictions.le("opertime",car.getTime()));
            }
        }
		return dc;
	}
	
	
}

