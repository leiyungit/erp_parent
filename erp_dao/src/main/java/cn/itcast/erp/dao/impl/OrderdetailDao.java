package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IOrderdetailDao;
import cn.itcast.erp.entity.Orderdetail;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class OrderdetailDao extends BaseDao<Orderdetail> implements IOrderdetailDao {

	
	/**
	 * 构建查询条件
	 * @param orderdetail1
	 * @param orderdetail2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Orderdetail orderdetail1,Orderdetail orderdetail2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Orderdetail.class);
		if(orderdetail1!=null){
			if(orderdetail1.getGoodsname()!=null &&  orderdetail1.getGoodsname().trim().length()>0)
			{
				dc.add(Restrictions.like("goodsname", orderdetail1.getGoodsname(), MatchMode.ANYWHERE));			
			}
			// 根据明细状态
			if(orderdetail1.getState()!=null)
			{
				dc.add(Restrictions.eq("state", orderdetail1.getState()));
			}
			// 根据订单查询
            if(null != orderdetail1.getOrders() && orderdetail1.getOrders().getUuid() != null){
                dc.add(Restrictions.eq("orders",orderdetail1.getOrders()));
            }
		
		}		
		return dc;
	}
	
	
}

