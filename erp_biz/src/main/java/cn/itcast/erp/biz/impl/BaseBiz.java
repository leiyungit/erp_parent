package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.dao.IBaseDao;
import java.util.List;

public class BaseBiz<T> implements IBaseBiz<T> {

    private IBaseDao<T> baseDao;

    public void setBaseDao(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<T> getList() {
        return this.baseDao.getList();
    }

    /**
     * 分页查询
     *
     * @param t1
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<T> getList(T t1, int firstResult, int maxResults) {
        return this.baseDao.getList(t1,firstResult,maxResults);
    }

    /**
     * 条件查询
     */
    public List<T> getList(T t1,T t2,Object param) {
        return baseDao.getList(t1,t2,param);
    }

    /**
     * 条件查询 分页
     *
     * @param t1
     * @param t2
     * @param param
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<T> listByPage(T t1, T t2, Object param, int firstResult, int maxResults) {
        return  this.baseDao.listByPage(t1,t2,param,firstResult,maxResults);
    }

    /**
     * 获取总记录数
     *
     * @param t1
     * @return
     */
    @Override
    public long getCount(T t1,T t2, Object param) {
        return this.baseDao.getCount(t1,t2,param);
    }

    /**
     * 新增
     *
     * @param t
     */
    @Override
    public void add(T t) {
        this.baseDao.add(t);
    }

    /**
     * 删除
     *
     * @param uuid
     */
    @Override
    public void delete(Long uuid) {
        this.baseDao.delete(uuid);
    }

    /**
     * 通过编号查询对象
     *
     * @param uuid
     * @return
     */
    @Override
    public T get(Long uuid) {
        return this.baseDao.get(uuid);
    }

    /**
     * 通过字符串编号查询对象
     *
     * @param uuid
     * @return
     */
    @Override
    public T get(String uuid) {
        return this.baseDao.get(uuid);
    }

    /**
     * 更新
     *
     * @param t
     */
    @Override
    public void update(T t) {
        this.baseDao.update(t);
    }
}
