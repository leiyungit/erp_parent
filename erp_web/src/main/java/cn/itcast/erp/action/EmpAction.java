package cn.itcast.erp.action;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Tree;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Action 
 * @author Administrator
 *
 */
public class EmpAction extends BaseAction<Emp> {

	private IEmpBiz empBiz;
	
	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		setBaseBiz(empBiz);
	}

    /**
     * 读取角色组
     */
    public void readEmpRoles(){
        try {
            List<Tree> treeList = this.empBiz.readEmpRoles(getId());
            ResultUtil.write(JSON.toJSONString(treeList));
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturnFail(e.getMessage());
        }
    }

    private String checkedStr;
    public String getCheckedStr() {
        return checkedStr;
    }
    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    /**
     * 更新角色组
     */
    public void  updateEmpRoles(){
        try {
            this.empBiz.updateEmpRoles(getId(),checkedStr);
            ResultUtil.ajaxReturnSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturnFail("保存失败，"+e.getMessage());
        }
    }
	
}
