package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Dep;

import java.util.List;

public interface IBaseDao<T> {

    List<T> getList();

    /**
     * 条件查询 分页
     * @param t1
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<T> getList(T t1,int firstResult, int maxResults);

    /**
     * 条件查询列表
     * @param t1
     * @return
     */
    List<T> getList(T t1,T t2,Object param);
    /**
     * 条件查询 分页
     * @param t1
     * @return
     */
    List<T> listByPage(T t1,T t2,Object param,int firstResult, int maxResults);

    /**
     * 记录条件查询的总记录数
     * @param t1
     * @return
     */
    long getCount(T t1);

    /**
     * 记录条件查询的总记录数
     * @param t1
     * @return
     */
    long getCount(T t1,T t2,Object param);


    /**
     * 新增
     * @param t
     */
    void add(T t);

    /**
     * 删除
     */
    void delete(Long uuid);

    /**
     * 通过编号查询对象
     * @param uuid
     * @return
     */
    T get(Long uuid);

    /**
     * 通过字符串编号查询对象
     * @param uuid
     * @return
     */
    T get(String uuid);
    /**
     * 更新
     */
    void update(T t);
}
