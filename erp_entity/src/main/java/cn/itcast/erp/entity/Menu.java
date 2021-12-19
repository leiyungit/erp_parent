package cn.itcast.erp.entity;

import java.util.List;

/**
 * 实体类
 * @author Administrator *
 */
public class Menu {	
	private String menuid;//菜单ID
	private String menuname;//菜单名称
	private String icon;//图标
	private String url;//URL
	private List<Emp> menus;
	// private String pid;//上级菜单ID

	public String getMenuid() {		
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {		
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIcon() {		
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {		
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Emp> getMenus() {
		return menus;
	}
	public void setMenus(List<Emp> menus) {
		this.menus = menus;
	}


}
