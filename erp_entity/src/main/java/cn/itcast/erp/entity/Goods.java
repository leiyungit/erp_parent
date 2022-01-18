package cn.itcast.erp.entity;
/**
 * 实体类
 * @author Administrator *
 */
public class Goods {
    private Long uuid;//编号
    private String name;//名称
    private String origin;//产地
    private String producer;//厂家
    private String unit;//计量单位
    private Double inprice;//进货价格
    private Double outprice;//销售价格
    // private Long goodstypeuuid;//商品类型
    private Goodstype goodstype; //商品类型  需配置xml映射
    // 新加字段
    private String spec; // 规格
    private String model; // 型号
    private String colour; // 颜色
    private Integer shelflife; // 保质期(天）
    private Double beginstorenum; // 期初库存
    private Double minsafenum; // 最低安全库存
    private Double maxsafenum; // 最高安全库存
    /*public Long getGoodstypeuuid() {
        return goodstypeuuid;
    }

    public void setGoodstypeuuid(Long goodstypeuuid) {
        this.goodstypeuuid = goodstypeuuid;
    }*/

    public Goodstype getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Goodstype goodstype) {
        this.goodstype = goodstype;
    }

    public Long getUuid() {
        return uuid;
    }
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Double getInprice() {
        return inprice;
    }
    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }
    public Double getOutprice() {
        return outprice;
    }
    public void setOutprice(Double outprice) {
        this.outprice = outprice;
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
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public Integer getShelflife() {
        return shelflife;
    }
    public void setShelflife(Integer shelflife) {
        this.shelflife = shelflife;
    }
    public Double getBeginstorenum() {
        return beginstorenum;
    }
    public void setBeginstorenum(Double beginstorenum) {
        this.beginstorenum = beginstorenum;
    }
    public Double getMinsafenum() {
        return minsafenum;
    }
    public void setMinsafenum(Double minsafenum) {
        this.minsafenum = minsafenum;
    }
    public Double getMaxsafenum() {
        return maxsafenum;
    }
    public void setMaxsafenum(Double maxsafenum) {
        this.maxsafenum = maxsafenum;
    }
}
