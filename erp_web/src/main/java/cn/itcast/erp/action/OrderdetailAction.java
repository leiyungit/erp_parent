package cn.itcast.erp.action;
import cn.itcast.erp.biz.IOrderdetailBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.utils.ResultUtil;

/**
 * Action 
 * @author Administrator
 *
 */
public class OrderdetailAction extends BaseAction<Orderdetail> {

	private IOrderdetailBiz orderdetailBiz;
	
	public void setOrderdetailBiz(IOrderdetailBiz orderdetailBiz) {
		this.orderdetailBiz = orderdetailBiz;
		setBaseBiz(orderdetailBiz);
	}
	// 入库 仓库
	private Long storeuuid;
	// 入库数量
	private Double storenum;
    public Long getStoreuuid() {
        return storeuuid;
    }
    public void setStoreuuid(Long storeuuid) {
        this.storeuuid = storeuuid;
    }
    public Double getStorenum() {
        return storenum;
    }
    public void setStorenum(Double storenum) {
        this.storenum = storenum;
    }

    public void doInStore(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        try {
            System.out.println("storeuuid: "+storeuuid +", storenum:"+storenum);
            orderdetailBiz.doInStore(getId(), storeuuid,storenum,loginUser.getUuid());
            ResultUtil.ajaxReturnSuccess("入库成功");
        }catch (ErpException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
        } catch (Exception e) {
            ResultUtil.ajaxReturnFail("入库失败");
            e.printStackTrace();
        }
    }
    public void doNotInStore(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        try {
            orderdetailBiz.doNotInStore(getId(), storeuuid,storenum,loginUser.getUuid());
            ResultUtil.ajaxReturnSuccess("入库成功");
        }catch (ErpException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
        } catch (Exception e) {
            ResultUtil.ajaxReturnFail("入库失败");
            e.printStackTrace();
        }
    }
}
