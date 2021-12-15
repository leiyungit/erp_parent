package cn.itcast.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

public class DepDao extends HibernateDaoSupport implements IDepDao {

	@Override
	public List<Dep> getList() {
		// TODO Auto-generated method stub
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}

    /**
     * 条件查询
     *
     * @param dep1
     * @return
     */
    @Override
    public List<Dep> getList(Dep dep1, int firstResult, int maxResults) {
        //DetachedCriteria dc = getDetachedCriteria(dep1);
        return this.getList(dep1,null,null,firstResult,maxResults);
    }

    @Override
	public List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults) {
        DetachedCriteria dc = getDetachedCriteria(dep1);
        return (List<Dep>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
	}

    /**
     * 记录条件查询的总记录数
     *
     * @param dep1
     * @return
     */
    @Override
    public long getCount(Dep dep1) {
        DetachedCriteria dc = getDetachedCriteria(dep1);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }

    @Override
	public long getCount(Dep dep1, Dep dep2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(Dep dep) {
        System.out.println(dep);
        // this.getHibernateTemplate().setCheckWriteOperations(false);
        this.getHibernateTemplate().save(dep);
	}

	@Override
	public void delete(Long uuid) {
        Dep dep = new Dep();
        dep.setUuid(uuid);
        this.getHibernateTemplate().delete(dep);
	}

	@Override
	public Dep get(Long uuid) {
		return (Dep) this.getHibernateTemplate().get("uuid",uuid);
	}

	@Override
	public void update(Dep dep) {
        this.getHibernateTemplate().save(dep);
	}

	private DetachedCriteria getDetachedCriteria(Dep dep1){
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
