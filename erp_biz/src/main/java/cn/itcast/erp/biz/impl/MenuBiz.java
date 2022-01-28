package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {

	private IMenuDao menuDao;
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		setBaseDao(menuDao);
	}


    /**
     * 根据员工编号获取菜单
     *
     * @param empUuid 员工编号
     * @return
     */
    @Override
    public Menu readMenusByEmpuuid(Long empUuid) {
        Menu menu = this.menuDao.get("0");
        List<Menu> menuList = this.menuDao.getMenusByEmpuuid(empUuid);
        Menu myMenu = cloneMenu(menu);
        Menu t1;
        Menu t2;
        // 复制一级菜单
        for (Menu m1 : menu.getMenus()) {
            t1 = cloneMenu(m1);
            // 复制二级菜单
            for (Menu m2 : m1.getMenus()) {
                // 如果用户包含该菜单
                if(menuList.contains(m2)){
                    t2 = cloneMenu(m2);
                    t1.getMenus().add(t2);
                }
            }
            // 如果二级菜单有值才添加一级菜单到根
            if(t1.getMenus().size()>0){
                myMenu.getMenus().add(t1);
            }
        }
        return myMenu;
    }

    private Menu cloneMenu(Menu src){
        Menu menu = new Menu();
        menu.setMenuid(src.getMenuid());
        menu.setMenuname(src.getMenuname());
        menu.setIcon(src.getIcon());
        menu.setUrl(src.getUrl());
        menu.setMenus(new ArrayList<>());
        return menu;
    }
}
