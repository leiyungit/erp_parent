package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoreoperBiz;
import cn.itcast.erp.entity.Storeoper;

/**
 * Action 
 * @author Administrator
 *
 */
public class StoreoperAction extends BaseAction<Storeoper> {

	private IStoreoperBiz storeoperBiz;
	
	public void setStoreoperBiz(IStoreoperBiz storeoperBiz) {
		this.storeoperBiz = storeoperBiz;
		setBaseBiz(storeoperBiz);
	}
	
	
}
