package cn.itcast.erp.entity;
/**
 * 实体类
 * @author Administrator *
 */
public class Storedetail {	
	private Long uuid;//编号
	private Long storeuuid;//仓库编号
	private Long goodsuuid;//商品编号
	private Double num;//数量

	public Long getUuid() {		
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
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

}
