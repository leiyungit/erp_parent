package cn.itcast.erp.action;
import cn.itcast.erp.biz.IRoleBiz;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Action 
 * @author Administrator
 *
 */
public class RoleAction extends BaseAction<Role> {

	private IRoleBiz roleBiz;
	
	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		setBaseBiz(roleBiz);
	}

    /**
     * 读取角色菜单
     */
    public void readRoleMenus() {
        List<Tree> treeList = this.roleBiz.readRoleMenus(getId());
        ResultUtil.write(JSON.toJSONString(treeList));
    }

    /**
     * 树结构勾选中的菜单Id字符串，多个值用逗号连接
     */
    private String checkedStr;
    public String getCheckedStr() {
        return checkedStr;
    }
    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    /**
     * 更新角色权限
     */
    public void updateRoleMenus(){
        try {
            // System.out.println("roleId:"+getId()+ ", checkedStr:"+checkedStr);
            this.roleBiz.updateRoleMenus(getId(),checkedStr);
            ResultUtil.ajaxReturnSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturnFail("保存失败");
        }
    }
	
}
