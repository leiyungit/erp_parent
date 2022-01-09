package cn.itcast.erp.entity;
/**
 * 实体类
 * @author Administrator *
 */
public class Storedetail {	
	private Long uuid;//编号
	private Long storeuuid;//仓库编号
	private String storeName;
	private Long goodsuuid;//商品编号
	private String goodsName;
	private Double num;//数量

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

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
