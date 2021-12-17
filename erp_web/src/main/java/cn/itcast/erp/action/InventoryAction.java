package cn.itcast.erp.action;
import cn.itcast.erp.biz.IInventoryBiz;
import cn.itcast.erp.entity.Inventory;

/**
 * Action 
 * @author Administrator
 *
 */
public class InventoryAction extends BaseAction<Inventory> {

	private IInventoryBiz inventoryBiz;
	
	public void setInventoryBiz(IInventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
		setBaseBiz(inventoryBiz);
	}
	
	
}
