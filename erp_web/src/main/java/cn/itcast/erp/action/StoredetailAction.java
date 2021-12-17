package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.entity.Storedetail;

/**
 * Action 
 * @author Administrator
 *
 */
public class StoredetailAction extends BaseAction<Storedetail> {

	private IStoredetailBiz storedetailBiz;
	
	public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
		this.storedetailBiz = storedetailBiz;
		setBaseBiz(storedetailBiz);
	}
	
	
}
