package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.biz.enums.OrderDetailStateEnum;
import cn.itcast.erp.biz.enums.OrdersStateEnum;
import cn.itcast.erp.biz.enums.OrdersTypeEnum;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class OrdersBiz extends BaseBiz<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;

    public void setOrdersDao(IOrdersDao ordersDao) {
        this.ordersDao = ordersDao;
        setBaseDao(ordersDao);
    }
    public void setEmpDao(IEmpDao empDao) {
        this.empDao = empDao;
    }
    public void setSupplierDao(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }


    @Override
    public List<Orders> listByPage(Orders t1, Orders t2, Object param, int firstResult, int maxResults) {
        System.out.println("分页查询订单主表");
        List<Orders> orders = this.ordersDao.listByPage(t1, t2, param, firstResult, maxResults);
        // 缓存员工名称
        Map<Long,String> empMap = new HashMap<>();
        // 缓存供应商名称
        Map<Long,String> supplierMap = new HashMap<>();
        //Long start = System.currentTimeMillis();
        orders.stream().forEach(e->{
            e.setCreaterName(this.getEmpName(e.getCreater(),empMap));
            e.setCheckerName(this.getEmpName(e.getChecker(),empMap));
            e.setStarterName(this.getEmpName(e.getStarter(),empMap));
            e.setEnderName(this.getEmpName(e.getEnder(),empMap));
            e.setSupplierName(this.getSupplierName(e.getSupplieruuid(),supplierMap));  // 设置供应商名称
        });
        //Long end = System.currentTimeMillis();
        //System.out.println("A方案耗时："+(end-start));
       /* Long start = System.currentTimeMillis();
        orders.stream().map(e->{
            e.setCreaterName(this.getEmpName(e.getCreater(),empMap));
            e.setCheckerName(this.getEmpName(e.getChecker(),empMap));
            e.setStarterName(this.getEmpName(e.getStarter(),empMap));
            e.setEnderName(this.getEmpName(e.getEnder(),empMap));
            e.setSupplierName(this.getSupplierName(e.getSupplieruuid(),supplierMap));
            return e;
        }).collect(Collectors.toList());
        Long end = System.currentTimeMillis();
        System.out.println("B方案耗时："+(end-start));*/
        return orders;
    }

    @Override
    public void add(Orders orders){
        // 新增采购订单状态
        orders.setState(OrdersStateEnum.NEW.getCode());
        // 采购/销售， 类型由前端传入
        // orders.setType(OrdersTypeEnum.PO.getCode());
        // 订单创建时间
        orders.setCreatetime(new Date());
        // 采购总金额
        double total = 0;
        double money = 0;
        Goods goods = null;
        for (Orderdetail orderDetail : orders.getOrderDetails()) {
            // 累计金额, 前端商品单价是可以修改的
            total += orderDetail.getMoney();
            // 获取商品信息中的采购单价，避免被篡改url提交的数据
            /*goods = goodsDao.get(orderDetail.getGoodsuuid());
            if(goods == null){
                throw new ERPException("订单关联的商品已失效");
            }
            money = DoubleUtil.mul(goods.getInprice(), Double.valueOf(orderDetail.getNum()));
            */
            //money = DoubleUtil.mul(orderDetail.getPrice(), Double.valueOf(orderDetail.getNum()));
            //total = DoubleUtil.add(total,money);
            // System.out.println(orderDetail.getGoodsname() + "=》数量："+Double.valueOf(orderDetail.getNum()) + "，小计："+money + "，   合计："+total);
            // 明细单状态
            orderDetail.setState(OrderDetailStateEnum.PO_NOT_IN.getCode());
            // 设置明细对应的订单。原因：orders采用级联更新，且外键的维护交给明细来维护
            orderDetail.setOrders(orders);
        }
        // 设置总金额
        orders.setTotalmoney(total);
        System.out.println(orders.getOrderDetails());
        this.ordersDao.add(orders);
    }

    /**
     * 根据员工编号获取员工名称
     * @param empUuid
     * @param empMap
     * @return
     */
    private String getEmpName(Long empUuid, Map<Long,String> empMap){
        if(empUuid == null){
            return null;
        }
        if(!empMap.containsKey(empUuid)){
            //System.out.print("=================key不存在，查询数据库"+empUuid);
            String name = this.empDao.get(empUuid).getName();
            //System.out.println(", name:"+name);
            empMap.put(empUuid, name);
            return name;
        }
        return empMap.get(empUuid);

    }

    private String getSupplierName(Long SupplierUuid, Map<Long,String> SupplierMap){
        if(SupplierUuid == null){
            return null;
        }
        if(!SupplierMap.containsKey(SupplierUuid)){
            String name = this.supplierDao.get(SupplierUuid).getName();
            SupplierMap.put(SupplierUuid, name);
            // System.out.println("供应商名称："+name);
            return name;
        }
        return SupplierMap.get(SupplierUuid);
    }

    /**
     * 采购订单审核
     *
     * @param uuid
     * @param empUuid
     */
    @Override
    public void doCheck(Long uuid, Long empUuid) {
        Orders orders =  this.ordersDao.get(uuid);
        if(orders.getState() != OrdersStateEnum.NEW.getCode()){
            throw new ErpException("该订单已审核过!");
        }
        orders.setChecker(empUuid);
        orders.setChecktime(new Date());
        orders.setState(OrdersStateEnum.CHECK.getCode());
        // this.ordersDao.update(orders);
    }

    /**
     * 采购订单确认
     *
     * @param uuid
     * @param empUuid
     */
    @Override
    public void doStart(Long uuid, Long empUuid) {
        Orders orders =  this.ordersDao.get(uuid);
        if(orders.getState() != OrdersStateEnum.CHECK.getCode()){
            throw new ErpException("该订单已确认过!");
        }
        orders.setStarter(empUuid);
        orders.setStarttime(new Date());
        orders.setState(OrdersStateEnum.START.getCode());
    }
}
