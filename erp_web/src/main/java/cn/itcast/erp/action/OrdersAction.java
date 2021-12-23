package cn.itcast.erp.action;
import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;

/**
 * Action 
 * @author Administrator
 *
 */
public class OrdersAction extends BaseAction<Orders> {

	private IOrdersBiz ordersBiz;
	
	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		setBaseBiz(ordersBiz);
	}

	// 接收订单明细的json格式的字符,数组形式的json字符串,里面的元素应该是每个订单明细
	private String json;
    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }

    public void add(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        try {
            Orders orders = this.getT();
            // 设置订单创建人
            orders.setChecker(loginUser.getUuid());
            // orders.setSupplieruuid(t1.getSupplieruuid());
            System.out.println(orders);
            List<Orderdetail> orderDetails = JSON.parseArray(json, Orderdetail.class);
            orders.setOrderDetails(orderDetails);
            // System.out.println(orders);
            ordersBiz.add(orders);
            ResultUtil.ajaxReturnSuccess("添加订单成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.ajaxReturnFail("添加订单失败");
        }
    }

    public void doCheck(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        try {
            ordersBiz.doCheck(getId(),loginUser.getUuid());
            ResultUtil.ajaxReturnSuccess("审核成功");
        }catch (ErpException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
        } catch (Exception e) {
            ResultUtil.ajaxReturnFail("审核失败");
            e.printStackTrace();
        }
    }

    public void doStart(){
        Emp loginUser = getLoginUser();
        if(null == loginUser){
            ResultUtil.ajaxReturnFail("亲，您还没有登录！");
            return;
        }
        try {
            ordersBiz.doStart(getId(),loginUser.getUuid());
            ResultUtil.ajaxReturnSuccess("确认成功");
        }catch (ErpException e) {
            ResultUtil.ajaxReturnFail(e.getMessage());
        } catch (Exception e) {
            ResultUtil.ajaxReturnFail("确认失败");
            e.printStackTrace();
        }
    }
}
