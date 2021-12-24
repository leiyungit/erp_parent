package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoreBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Store;
import cn.itcast.erp.utils.ResultUtil;

/**
 * 仓库
 * @author Administrator
 *
 */
public class StoreAction extends BaseAction<Store> {

	private IStoreBiz storeBiz;
	
	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		setBaseBiz(storeBiz);
	}
	
	public void myList(){
        if(null == getT1()){
            //构建查询条件
            setT1(new Store());
        }
        Emp loginUser = getLoginUser();
        if(loginUser == null){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        //登陆用户的编号查询
        getT1().setEmpuuid(loginUser.getUuid());
    }
}
