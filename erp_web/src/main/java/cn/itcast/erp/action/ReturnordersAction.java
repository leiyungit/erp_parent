package cn.itcast.erp.action;
import cn.itcast.erp.biz.IReturnordersBiz;
import cn.itcast.erp.entity.Returnorders;

/**
 * Action 
 * @author Administrator
 *
 */
public class ReturnordersAction extends BaseAction<Returnorders> {

	private IReturnordersBiz returnordersBiz;
	
	public void setReturnordersBiz(IReturnordersBiz returnordersBiz) {
		this.returnordersBiz = returnordersBiz;
		setBaseBiz(returnordersBiz);
	}
	
	
}
