package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IReturnordersDao;
import cn.itcast.erp.entity.Returnorders;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class ReturnordersDao extends BaseDao<Returnorders> implements IReturnordersDao {

	
	/**
	 * 构建查询条件
	 * @param returnorders1
	 * @param returnorders2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Returnorders returnorders1,Returnorders returnorders2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Returnorders.class);
		if(returnorders1!=null){
			if(returnorders1.getType()!=null &&  returnorders1.getType().trim().length()>0)
			{
				dc.add(Restrictions.like("type", returnorders1.getType(), MatchMode.ANYWHERE));			
			}
			if(returnorders1.getState()!=null &&  returnorders1.getState().trim().length()>0)
			{
				dc.add(Restrictions.like("state", returnorders1.getState(), MatchMode.ANYWHERE));			
			}
			if(returnorders1.getWaybillsn()!=null &&  returnorders1.getWaybillsn().trim().length()>0)
			{
				dc.add(Restrictions.like("waybillsn", returnorders1.getWaybillsn(), MatchMode.ANYWHERE));			
			}
		
		}		
		return dc;
	}
	
	
}

