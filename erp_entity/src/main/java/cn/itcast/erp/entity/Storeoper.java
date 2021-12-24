package cn.itcast.erp.entity;
/**
 * 仓库操作记录实体类
 * @author Administrator *
 */
public class Storeoper {
    private Long uuid;//编号
    private Long empuuid;//操作员工编号
    private java.util.Date opertime;//操作日期
    private Long storeuuid;//仓库编号
    private Long goodsuuid;//商品编号
    private Double num;//数量
    private Integer type;//1：入库 2：出库
    private String operdesc;  // 操作描述

    public String getOperdesc() {
        return operdesc;
    }
    public void setOperdesc(String operdesc) {
        this.operdesc = operdesc;
    }
    public Long getUuid() {
        return uuid;
    }
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    public Long getEmpuuid() {
        return empuuid;
    }
    public void setEmpuuid(Long empuuid) {
        this.empuuid = empuuid;
    }
    public java.util.Date getOpertime() {
        return opertime;
    }
    public void setOpertime(java.util.Date opertime) {
        this.opertime = opertime;
    }
    public Long getStoreuuid() {
        return storeuuid;
    }
    public void setStoreuuid(Long storeuuid) {
        this.storeuuid = storeuuid;
    }
    public Long getGoodsuuid() {
        return goodsuuid;
    }
    public void setGoodsuuid(Long goodsuuid) {
        this.goodsuuid = goodsuuid;
    }
    public Double getNum() {
        return num;
    }
    public void setNum(Double num) {
        this.num = num;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

}
