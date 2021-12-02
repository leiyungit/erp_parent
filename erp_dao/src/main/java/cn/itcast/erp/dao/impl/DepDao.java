package cn.itcast.erp.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

public class DepDao extends HibernateDaoSupport implements IDepDao {

	@Override
	public List<Dep> getList() {
		// TODO Auto-generated method stub
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}

	@Override
	public List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(Dep dep1, Dep dep2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(Dep dep) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Dep get(Long uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Dep dep) {
		// TODO Auto-generated method stub

	}

}
