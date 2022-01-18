package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IGoodstypeDao;
import cn.itcast.erp.entity.Goodstype;
/**
 * 数据访问类
 * @author Administrator
 *
 */
public class GoodstypeDao extends BaseDao<Goodstype> implements IGoodstypeDao {

	
	/**
	 * 构建查询条件
	 * @param goodstype1
	 * @param goodstype2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Goodstype goodstype1,Goodstype goodstype2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Goodstype.class);
		if(goodstype1!=null){
			if(goodstype1.getName()!=null &&  goodstype1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", goodstype1.getName(), MatchMode.ANYWHERE));			
			}
		
		}
        if(goodstype2!=null){
            if(goodstype2.getName()!=null &&  goodstype2.getName().trim().length()>0)
            {
                dc.add(Restrictions.eq("name", goodstype2.getName()));
            }

        }
        return dc;
	}
	
	
}

