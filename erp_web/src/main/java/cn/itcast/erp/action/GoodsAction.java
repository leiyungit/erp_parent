package cn.itcast.erp.action;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.entity.Goods;

/**
 * Action 
 * @author Administrator
 *
 */
public class GoodsAction extends BaseAction<Goods> {

	private IGoodsBiz goodsBiz;
	
	public void setGoodsBiz(IGoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		setBaseBiz(goodsBiz);
	}
	
	
}