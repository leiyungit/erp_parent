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

    @Override
    public List<Dep> getList(Dep dep1,int firstResult, int maxResults) {
        return this.depDao.getList(dep1,firstResult,maxResults);
    }

    @Override
    public long getCount(Dep dep1) {
        return this.depDao.getCount(dep1);
    }

    /**
     * 新增
     *
     * @param dep
     */
    @Override
    public void add(Dep dep) {
        this.depDao.add(dep);
    }

    /**
     * 删除
     *
     * @param uuid
     */
    @Override
    public void delete(Long uuid) {
        this.depDao.delete(uuid);
    }

    /**
     * 通过编号查询对象
     *
     * @param uuid
     * @return
     */
    @Override
    public Dep get(Long uuid) {
        return this.depDao.get(uuid);
    }

    /**
     * 更新
     *
     * @param dep
     */
    @Override
    public void update(Dep dep) {
        this.depDao.update(dep);
    }

}
