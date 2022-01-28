package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Menu;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface IMenuDao extends IBaseDao<Menu>{

    /**
     * 根据员工编号获取菜单
     * @param empUuid 员工编号
     * @return
     */
	List<Menu> getMenusByEmpuuid(Long empUuid);
}
