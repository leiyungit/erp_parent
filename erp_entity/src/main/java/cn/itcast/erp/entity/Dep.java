package cn.itcast.erp.entity;

/**
 * 部门
 */
public class Dep {

	/** 部门编号 **/
	private Long uuid;
	
	/**
	 * 部门名称
	 */
	private String name;
	
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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	/**
	 * 联系电话
	 */
	private String tele;

	@Override
	public String toString() {
		return "Dep [uuid=" + uuid + ", name=" + name + ", tele=" + tele + "]";
	}
	
	
}