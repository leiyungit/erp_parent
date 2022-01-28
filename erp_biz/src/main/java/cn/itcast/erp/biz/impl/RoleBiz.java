package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IRoleBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.dao.IRoleDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Menu;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class RoleBiz extends BaseBiz<Role> implements IRoleBiz {

	private IRoleDao roleDao;
	private IMenuDao menuDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		setBaseDao(roleDao);
	}

    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /**
     * 读取角色菜单
     *
     * @param roleuuid 角色Id
     * @return
     */
    @Override
    public List<Tree> readRoleMenus(Long roleuuid) {
        // System.out.println("角色Id："+roleuuid);
        List<Tree> treeList = new ArrayList<>();
        Role role = this.roleDao.get(roleuuid);
        List<Menu> roleMenus = null;// 获取角色菜单权限表数据，中间表，角色对应的菜单数据
        if(null != role){
            roleMenus = role.getMenus();
            // System.out.println("角色菜单权限size："+roleMenus.size());
        }
        Menu root = menuDao.get("0"); // 获取菜单根节点
        Tree t1 = null;
        Tree t2 = null;
        // 一级菜单
        for (Menu m1 : root.getMenus()) {
            t1 = new Tree();
            t1.setId(m1.getMenuid());
            t1.setText(m1.getMenuname());
            // 二级菜单
            for (Menu m2 : m1.getMenus()) {
                // System.out.println("m1.getMenus()："+ m1.getMenus().size()+", m2:"+m2.getMenuname());
                t2 = new Tree();
                t2.setId(m2.getMenuid());
                t2.setText(m2.getMenuname());
                // 设置已有权限菜单
                if(null != roleMenus && roleMenus.contains(m2)){
                    t2.setChecked(true);
                }
                t1.getChildren().add(t2);
            }
            treeList.add(t1);
        }
        return treeList;
    }

    /**
     * 更新角色菜单
     *
     * @param uuid       角色Id
     * @param checkedStr 菜单Id,多个值用逗号连接，如 100,102,103
     */
    @Override
    public void updateRoleMenus(Long uuid, String checkedStr) {
        // System.out.println("checkedStr:"+checkedStr);
        if(null == uuid){
            throw new ErpException("请先选择角色");
        }
        Role role = this.roleDao.get(uuid);
        List<Menu> roleMenus = role.getMenus();// 获取角色菜单权限表数据，中间表，角色对应的菜单数据
        if(null == role){
            throw new ErpException("角色未定义！");
        }
        // 清空角色ID下的菜单权限
        role.setMenus(new ArrayList<Menu>());
        if(StringUtils.isBlank(checkedStr)){
            return;
        }
        String[] menuIds = checkedStr.split(",");
        Menu menu = null;
        for (String menuId : menuIds) {
            menu = this.menuDao.get(menuId);
            role.getMenus().add(menu);
        }
    }
}
