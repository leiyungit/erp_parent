package cn.itcast.erp.action;
import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.entity.Menu;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

/**
 * Action 
 * @author Administrator
 *
 */
public class MenuAction extends BaseAction<Menu> {

	private IMenuBiz menuBiz;
	
	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		setBaseBiz(menuBiz);
	}

	public void getMenuTree(){
		Menu menu = this.menuBiz.get("0");
		String jsonString = JSON.toJSONString(menu,true);
		ResultUtil.write(jsonString);
	}
	
}
