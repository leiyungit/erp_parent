package cn.itcast.erp.entity;

/**
 * 库存报警 （视图）
 */
public class Storealert {
    private Long uuid;      // 商品编号
    private String name;    // 商品名称
    private Double storenum;// 库存数量
    private Double innum;   // 待入库数量
    private Double outnum;  // 待发货数量

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

    public Double getStorenum() {
        return storenum;
    }

    public void setStorenum(Double storenum) {
        this.storenum = storenum;
    }

    public Double getInnum() {
        return innum;
    }

    public void setInnum(Double innum) {
        this.innum = innum;
    }

    public Double getOutnum() {
        return outnum;
    }

    public void setOutnum(Double outnum) {
        this.outnum = outnum;
    }
}
