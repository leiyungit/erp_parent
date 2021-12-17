package cn.itcast.erp.action;
import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.entity.Goodstype;

/**
 * Action 
 * @author Administrator
 *
 */
public class GoodstypeAction extends BaseAction<Goodstype> {

	private IGoodstypeBiz goodstypeBiz;
	
	public void setGoodstypeBiz(IGoodstypeBiz goodstypeBiz) {
		this.goodstypeBiz = goodstypeBiz;
		setBaseBiz(goodstypeBiz);
	}
	
	
}
