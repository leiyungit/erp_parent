package cn.itcast.erp.action;
import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Menu;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

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

	public void readMenusByEmpuuid(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("请先登录");
            return;
        }
        Menu menu = this.menuBiz.readMenusByEmpuuid(loginUser.getUuid());
        String jsonString = JSON.toJSONString(menu);
        ResultUtil.write(jsonString);
    }
	
}
