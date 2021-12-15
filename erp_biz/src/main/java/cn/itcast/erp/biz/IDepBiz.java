package cn.itcast.erp.biz;
import java.util.List;

import cn.itcast.erp.entity.Dep;



public interface IDepBiz {
	
	List<Dep> getList();

	List<Dep> getList(Dep dep1,int firstResult, int maxResults);

    long getCount(Dep dep1);

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
