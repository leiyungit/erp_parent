package cn.itcast.erp.entity;
/**
 * 实体类
 * @author Administrator *
 */
public class Goodstype {	
	private Long uuid;//商品类型编号
	private String name;//商品类型名称

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

}