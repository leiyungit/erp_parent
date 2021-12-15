package cn.itcast.erp.dao;

import java.util.List;

import cn.itcast.erp.entity.Dep;

public interface IDepDao {

    List<Dep> getList();

    /**
     * 条件查询
     * @param dep1
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<Dep> getList(Dep dep1,int firstResult, int maxResults);
    /**
     * 条件查询
     * @param dep1
     * @return
     */
    List<Dep> listByPage(Dep dep1,Dep dep2,Object param,int firstResult, int maxResults);

    /**
     * 记录条件查询的总记录数
     * @param dep1
     * @return
     */
    long getCount(Dep dep1);

    /**
     * 记录条件查询的总记录数
     * @param dep1
     * @return
     */
    long getCount(Dep dep1,Dep dep2,Object param);


    /**
     * 新增
     * @param dep
     */
    void add(Dep dep);

    /**
     * 删除
     */
    void delete(Long uuid);

    /**
     * 通过编号查询对象
     * @param uuid
     * @return
     */
    Dep get(Long uuid);

    /**
     * 更新
     */
    void update(Dep dep);
	
}
