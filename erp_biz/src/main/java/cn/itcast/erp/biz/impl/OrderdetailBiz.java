package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IOrderdetailBiz;
import cn.itcast.erp.dao.IOrderdetailDao;
import cn.itcast.erp.entity.Orderdetail;
/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class OrderdetailBiz extends BaseBiz<Orderdetail> implements IOrderdetailBiz {

	private IOrderdetailDao orderdetailDao;
	
	public void setOrderdetailDao(IOrderdetailDao orderdetailDao) {
		this.orderdetailDao = orderdetailDao;
		setBaseDao(orderdetailDao);
	}


    /**
     * 采购入库
     *
     * @param uuid      采购明细编号
     * @param storeUuid 仓库编号
     * @param storeNum  入库数量
     * @param empUuid   库管员编号
     * @return 是否成功
     */
    @Override
    public boolean doInStore(Long uuid, Long storeUuid, Double storeNum, Long empUuid) {
        // 1.获取明细单
        // 2.更改明细单状态，已入库数量，仓库位置
        // 3、商品仓库库存更新
        // 4、库存记录表，添加记录
        // 5、遍历明细表，判断是否更新主表状态
        return false;
    }
}
