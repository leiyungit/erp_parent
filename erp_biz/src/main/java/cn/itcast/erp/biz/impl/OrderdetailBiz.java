package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IOrderdetailBiz;
import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.biz.constant.OrderConstant;
import cn.itcast.erp.biz.enums.OrderDetailStateEnum;
import cn.itcast.erp.biz.enums.OrdersStateEnum;
import cn.itcast.erp.biz.enums.StoreoperTypeEnum;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.biz.utils.DoubleUtil;
import cn.itcast.erp.dao.IOrderdetailDao;
import cn.itcast.erp.dao.IStoredetailDao;
import cn.itcast.erp.dao.IStoreoperDao;
import cn.itcast.erp.entity.*;
import cn.itcast.redsun.ws.impl.IWaybillWs;

import java.util.Date;
import java.util.List;

/**
 * 业务逻辑类
 *
 * @author Administrator
 */
public class OrderdetailBiz extends BaseBiz<Orderdetail> implements IOrderdetailBiz {

    private IOrderdetailDao orderdetailDao;
    // 商品仓库库存
    private IStoredetailDao storedetailDao;
    // 库存变更记录
    private IStoreoperDao storeoperDao;
    // 供应商
    private ISupplierBiz supplierBiz;
    // webService
    private IWaybillWs waybillWs;

    public void setSupplierBiz(ISupplierBiz supplierBiz) {
        this.supplierBiz = supplierBiz;
    }

    public void setWaybillWs(IWaybillWs waybillWs) {
        this.waybillWs = waybillWs;
    }

    public void setOrderdetailDao(IOrderdetailDao orderdetailDao) {
        this.orderdetailDao = orderdetailDao;
        setBaseDao(orderdetailDao);
    }

    public void setStoredetailDao(IStoredetailDao storedetailDao) {
        this.storedetailDao = storedetailDao;
    }

    public void setStoreoperDao(IStoreoperDao storeoperDao) {
        this.storeoperDao = storeoperDao;
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
        System.out.println(uuid);
        // 1.获取明细单
        Orderdetail detail = this.orderdetailDao.get(uuid);
        // System.out.println(detail);
        // 如果已经全部入库，则不允许再次入库
        if (detail.getState() == OrderDetailStateEnum.PO_IN.getCode()) {
            throw new ErpException(detail.getGoodsname() + "，已全部入库！");
        }
        if (null == storeNum || 0 == storeNum) {
            throw new ErpException(detail.getGoodsname() + "，入库数量不可以为0！");
        }
        // 2.更改明细单状态，已入库数量，仓库
        detail.setEnder(empUuid);
        detail.setEndtime(new Date());
        Double dataStoreNum = detail.getStorenum() == null ? 0 : detail.getStorenum(); // 已 入库/出库数量
        if (dataStoreNum + storeNum < detail.getNum()) {
            detail.setState(OrderDetailStateEnum.PO_PART_IN.getCode()); // 部分入库
        } else {
            detail.setState(OrderDetailStateEnum.PO_IN.getCode());
        }
        // 入库数量
        detail.setStorenum(DoubleUtil.add(dataStoreNum, storeNum));
        // 入库仓库
        detail.setStoreuuid(storeUuid);
        System.out.println("订单明细，已入库数量：" + detail.getStorenum() + ",  计算：" + DoubleUtil.add(dataStoreNum, storeNum));
        // 3、更新商品仓库库存
        //      根据商品编辑和仓库编号查询是否已经存在库存记录, 构建查询条件
        Storedetail storedetail = new Storedetail();
        storedetail.setGoodsuuid(detail.getGoodsuuid());
        storedetail.setStoreuuid(storeUuid);
        List<Storedetail> storedetailList = storedetailDao.getList(storedetail, null, null);
        if (storedetailList.size() > 0) {
            // 库存表已有记录时，获取库存表的数量，为空转换0
            Double num = storedetailList.get(0).getNum() == null ? 0 : storedetailList.get(0).getNum();
            storedetailList.get(0).setNum(DoubleUtil.add(num, storeNum));
        } else {
            storedetail.setNum(storeNum);
            storedetailDao.add(storedetail);
        }

        // 4、库存记录表，添加记录
        Storeoper storeoper = new Storeoper();
        storeoper.setEmpuuid(empUuid);
        storeoper.setGoodsuuid(detail.getGoodsuuid());
        storeoper.setNum(storeNum);
        storeoper.setStoreuuid(storeUuid);
        storeoper.setType(StoreoperTypeEnum.IN.getCode());
        storeoper.setOpertime(detail.getEndtime()); // 入库时间
        storeoper.setOperdesc(OrderConstant.OrderPoIn);
        storeoperDao.add(storeoper);
        // 5、遍历明细表，判断是否更新主表状态
        // 5.1 获取明细对应的订单主表
        Orders orders = detail.getOrders();
        // 5.2 统计该订单所有state=2(已全部入库)的明细个数，看是否还存在没有入库的明细
        //     构建查询条件
        Orderdetail detailParam = new Orderdetail();
        detailParam.setOrders(orders);
        long count = this.orderdetailDao.getCount(detailParam, null, null);
        detailParam.setState(OrderDetailStateEnum.PO_IN.getCode());
        long countIn = this.orderdetailDao.getCount(detailParam, null, null);
        if (count == countIn) {
            // 如果已经全部入库
            orders.setEnder(empUuid);
            orders.setEndtime(detail.getEndtime());
            orders.setState(OrdersStateEnum.END.getCode());
        }
        return true;
    }

    /**
     * 采购入库-撤销
     *
     * @param uuid      采购明细编号
     * @param storeUuid 仓库编号
     * @param storeNum  入库-撤回数量
     * @param empUuid   库管员编号
     * @return 是否成功
     */
    @Override
    public boolean doNotInStore(Long uuid, Long storeUuid, Double storeNum, Long empUuid) {
        // 1.获取明细单
        Orderdetail detail = this.orderdetailDao.get(uuid);
        // 如果未入库，则不允许撤回
        if (detail.getState() == OrderDetailStateEnum.PO_NOT_IN.getCode()) {
            throw new ErpException(detail.getGoodsname() + "，未入库，无法撤回!");
        }
        if (null == storeNum || 0 == storeNum) {
            throw new ErpException(detail.getGoodsname() + "，入库数量不可以为0!");
        }
        if (null == detail.getStorenum() || detail.getStorenum() < storeNum) {
            throw new ErpException(detail.getGoodsname() + "，撤回数量大于已入库数量：" + detail.getStorenum() + "，无法撤回!");
        }
        // 2.更改明细单状态，已入库数量，仓库
        detail.setEnder(null);
        detail.setEndtime(null);
        // 已 入库/出库数量 == 撤回数量
        if (detail.getStorenum() == storeNum) {
            detail.setState(OrderDetailStateEnum.PO_NOT_IN.getCode()); // 未入库
        } else {
            detail.setState(OrderDetailStateEnum.PO_PART_IN.getCode());
        }
        // 入库数量
        detail.setStorenum(DoubleUtil.sub(detail.getStorenum(), storeNum));
        // 入库仓库
        detail.setStoreuuid(storeUuid);
        // 3、更新商品仓库库存
        //      根据商品编辑和仓库编号查询是否已经存在库存记录, 构建查询条件
        Storedetail storedetail = new Storedetail();
        storedetail.setGoodsuuid(detail.getGoodsuuid());
        storedetail.setStoreuuid(storeUuid);
        List<Storedetail> storedetailList = storedetailDao.getList(storedetail, null, null);
        if (storedetailList.size() == 0) {
            throw new ErpException(detail.getGoodsname() + "，没有找打库存记录，无法撤回，仓库编码:" + storeUuid);
        }
        Double dataStoreNum = storedetailList.get(0).getNum();
        if (dataStoreNum < storeNum) {
            throw new ErpException(detail.getGoodsname() + "，库存数量小于回撤数量，无法撤回，仓库编码:" + storeUuid);
        }
        storedetailList.get(0).setNum(DoubleUtil.sub(dataStoreNum, storeNum));
        // 4、库存记录表，添加记录
        Storeoper storeoper = new Storeoper();
        storeoper.setEmpuuid(empUuid);
        storeoper.setGoodsuuid(detail.getGoodsuuid());
        storeoper.setNum(storeNum * -1);
        storeoper.setStoreuuid(storeUuid);
        storeoper.setType(StoreoperTypeEnum.IN.getCode());
        storeoper.setOpertime(new Date()); // 入库时间
        storeoper.setOperdesc(OrderConstant.OrderPoInNot);
        storeoperDao.add(storeoper);
        // 5、遍历明细表，判断是否更新主表状态
        // 5.1 获取明细对应的订单主表
        Orders orders = detail.getOrders();
        if (orders.getState().equals(OrdersStateEnum.END.getCode())) {
            // 撤回
            orders.setEnder(null);
            orders.setEndtime(null);
            orders.setState(OrdersStateEnum.START.getCode());
        }
        return true;
    }

    /**
     * 销售出库
     *
     * @param uuid      销售明细单号
     * @param storeUuid 仓库编号
     * @param storeNum  出库数量
     * @param empUuid   库管员
     * @return
     */
    @Override
    public boolean doOutStore(Long uuid, Long storeUuid, Double storeNum, Long empUuid) {
        // 1.获取明细单
        Orderdetail detail = this.orderdetailDao.get(uuid);
        // System.out.println(detail);
        // 如果已经全部出库，则不允许再次出库
        if (detail.getState() == OrderDetailStateEnum.SO_OUT.getCode()) {
            throw new ErpException(detail.getGoodsname() + "，已全部出库！");
        }
        if (null == storeNum || 0 == storeNum) {
            throw new ErpException(detail.getGoodsname() + "，出库数量不可以为0！");
        }
        // 2.更改明细单状态，已入库数量，仓库
        detail.setEnder(empUuid);
        detail.setEndtime(new Date());
        Double dataStoreNum = detail.getStorenum() == null ? 0 : detail.getStorenum(); // 已 入库/出库数量
        if (dataStoreNum + storeNum < detail.getNum()) {
            detail.setState(OrderDetailStateEnum.SO_PART_OUT.getCode()); // 部分出库
        } else {
            detail.setState(OrderDetailStateEnum.SO_OUT.getCode());
        }
        // 已出库数量
        detail.setStorenum(DoubleUtil.add(dataStoreNum, storeNum));
        // 出库仓库
        detail.setStoreuuid(storeUuid);
        System.out.println("订单明细，已出库数量：" + detail.getStorenum() + ",  计算：" + DoubleUtil.add(dataStoreNum, storeNum));
        // 3、更新商品仓库库存
        //      根据商品编辑和仓库编号查询是否已经存在库存记录, 构建查询条件
        Storedetail storedetail = new Storedetail();
        storedetail.setGoodsuuid(detail.getGoodsuuid());
        storedetail.setStoreuuid(storeUuid);
        List<Storedetail> storedetailList = storedetailDao.getList(storedetail, null, null);
        if (storedetailList.size() > 0 && storedetailList.get(0).getNum() != null) {
            Double num = storedetailList.get(0).getNum();
            storedetailList.get(0).setNum(DoubleUtil.sub(num, storeNum));
            if (storedetailList.get(0).getNum() < 0) {
                throw new ErpException("仓库：" + storeUuid + "，商品：" + detail.getGoodsname() + "，库存不足");
            }
        }
        else {
            throw new ErpException("仓库：" + storeUuid + "，商品：" + detail.getGoodsname() + "，没有库存");
        }

        // 4、库存记录表，添加记录
        Storeoper storeoper = new Storeoper();
        storeoper.setEmpuuid(empUuid);
        storeoper.setGoodsuuid(detail.getGoodsuuid());
        storeoper.setNum(storeNum);
        storeoper.setStoreuuid(storeUuid);
        storeoper.setType(StoreoperTypeEnum.OUT.getCode());
        storeoper.setOpertime(detail.getEndtime()); // 入库时间
        storeoper.setOperdesc(OrderConstant.OrderSoIn);
        storeoperDao.add(storeoper);
        // 5、遍历明细表，判断是否更新主表状态
        // 5.1 获取明细对应的订单主表
        Orders orders = detail.getOrders();
        // 5.2 统计该订单所有state=2(已全部出库)的明细个数，看是否还存在没有出库的明细
        //     构建查询条件
        Orderdetail detailParam = new Orderdetail();
        detailParam.setOrders(orders);
        long count = this.orderdetailDao.getCount(detailParam, null, null);
        detailParam.setState(OrderDetailStateEnum.SO_OUT.getCode());
        long countOut = this.orderdetailDao.getCount(detailParam, null, null);
        if (count == countOut) {
            // 如果已经全部出库
            orders.setEnder(empUuid);
            orders.setEndtime(detail.getEndtime());
            orders.setState(OrdersStateEnum.OUT.getCode());
            Supplier supplier = this.supplierBiz.get(orders.getSupplieruuid());
            Long userId = 5l; // 用户id
            // Long userId, String toAddress, String addressee, String tele, String info
            this.waybillWs.addWaybill(userId,supplier.getAddress(),supplier.getName() + "/" + supplier.getContact(),supplier.getTele(),"--");
            // TODO：最后新加一个销售出库表单，如上运单号应该绑定到出库单中
        }
        return true;
    }

}
