package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;

import java.util.List;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IRoleBiz extends IBaseBiz<Role>{

    /**
     * 读取角色菜单
     * @param roleuuid 角色Id
     * @return
     */
	List<Tree> readRoleMenus(Long roleuuid);

    /**
     * 更新角色菜单
     * @param uuid 角色Id
     * @param checkedStr 菜单Id,多个值用逗号连接，如 100,102,103
     */
	void updateRoleMenus(Long uuid, String checkedStr);
}

