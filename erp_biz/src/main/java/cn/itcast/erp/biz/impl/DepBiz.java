package cn.itcast.erp.biz.impl;

import java.util.List;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

/**
 * 部门业务实现
 * @author Administrator
 *
 */
public class DepBiz implements IDepBiz {
	
	/** 数据访问注入*/
	private IDepDao depDao;
	
	
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
	}


	@Override
	public List<Dep> getList() {
		return this.depDao.getList();
	}

}
