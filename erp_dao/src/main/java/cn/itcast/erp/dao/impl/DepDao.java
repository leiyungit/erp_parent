package cn.itcast.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

public class DepDao extends BaseDao<Dep> implements IDepDao {

	/*@Override
	public List<Dep> getList() {
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}*/


	public DetachedCriteria getDetachedCriteria(Dep dep1){
        DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
        if(null != dep1){
            if(null != dep1.getName() && dep1.getName().trim().length()>0){
                //MatchMode.ANYWHERE => % %
                //MatchMode.END =>      %name
                //MatchMode.START  =>   name%
                dc.add(Restrictions.like("name",dep1.getName(), MatchMode.ANYWHERE));
            }
            if(null != dep1.getTele() && dep1.getTele().trim().length() >0){
                dc.add(Restrictions.like("tele",dep1.getTele(),MatchMode.ANYWHERE));
            }
        }
        return dc;
    }
}
