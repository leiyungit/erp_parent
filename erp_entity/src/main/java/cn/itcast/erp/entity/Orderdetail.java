package cn.itcast.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单明细实体类
 * @author Administrator *
 */
public class Orderdetail {
    private Long uuid;//编号
    private Long goodsuuid;//商品编号
    private String goodsname;//商品名称
    private Double price;//价格
    private Double num;//数量
    private Double money;//金额
    private java.util.Date endtime;//结束日期
    private Long ender;//库管员
    private Long storeuuid;//仓库编号
    private Double storenum; // 入库/出库数量
    private Integer state;//采购：0=未入库，1=部分入库，2=已入库  销售：0=未出库，1=部分出库，2=已出库
    // private Long ordersuuid;//订单编号
    @JSONField(serialize = false) // fastJson在转换成字符串时反复的循环调用其属性造成的死循环
    private Orders orders; // 订单主表
    // 新加字段
    private String spec; // 规格
    private String model; // 型号
    // 加两个字段，已入库数量，最后入库时间

    public Double getStorenum() {
        return storenum;
    }
    public void setStorenum(Double storenum) {
        this.storenum = storenum;
    }
    public Long getUuid() {
        return uuid;
    }
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    public Long getGoodsuuid() {
        return goodsuuid;
    }
    public void setGoodsuuid(Long goodsuuid) {
        this.goodsuuid = goodsuuid;
    }
    public String getGoodsname() {
        return goodsname;
    }
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getNum() {
        return num;
    }
    public void setNum(Double num) {
        this.num = num;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
    public java.util.Date getEndtime() {
        return endtime;
    }
    public void setEndtime(java.util.Date endtime) {
        this.endtime = endtime;
    }
    public Long getEnder() {
        return ender;
    }
    public void setEnder(Long ender) {
        this.ender = ender;
    }
    public Long getStoreuuid() {
        return storeuuid;
    }
    public void setStoreuuid(Long storeuuid) {
        this.storeuuid = storeuuid;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    /*public Long getOrdersuuid() {
        return ordersuuid;
    }
    public void setOrdersuuid(Long ordersuuid) {
        this.ordersuuid = ordersuuid;
    }*/
    public Orders getOrders() {
        return orders;
    }
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    public String getSpec() {
        return spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "uuid=" + uuid +
                ", goodsuuid=" + goodsuuid +
                ", goodsname='" + goodsname + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", money=" + money +
                ", endtime=" + endtime +
                ", ender=" + ender +
                ", storeuuid=" + storeuuid +
                ", storenum=" + storenum +
                ", state=" + state +
                '}';
    }
}
