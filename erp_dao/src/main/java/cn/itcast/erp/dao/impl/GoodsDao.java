package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {

	
	/**
	 * 构建查询条件
	 * @param goods1
	 * @param goods2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Goods goods1,Goods goods2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Goods.class);
		if(goods1 != null){
			if(goods1.getName()!=null &&  goods1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));			
			}
			if(goods1.getOrigin()!=null &&  goods1.getOrigin().trim().length()>0)
			{
				dc.add(Restrictions.like("origin", goods1.getOrigin(), MatchMode.ANYWHERE));			
			}
			if(goods1.getProducer()!=null &&  goods1.getProducer().trim().length()>0)
			{
				dc.add(Restrictions.like("producer", goods1.getProducer(), MatchMode.ANYWHERE));			
			}
			if(goods1.getUnit()!=null &&  goods1.getUnit().trim().length()>0)
			{
				dc.add(Restrictions.like("unit", goods1.getUnit(), MatchMode.ANYWHERE));			
			}
		
		}
		if(null != goods2){
            if(null != goods2.getName() &&  goods2.getName().trim().length()>0)
            {
                dc.add(Restrictions.eq("name", goods2.getName()));
            }
        }
		// todo: 1.价格区间查询；2.商品类型查询
		return dc;
	}
	
	
}

