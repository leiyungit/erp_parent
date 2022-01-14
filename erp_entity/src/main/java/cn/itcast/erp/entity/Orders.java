package cn.itcast.erp.entity;

import java.util.Date;
import java.util.List;

/**
 * 实体类
 * @author Administrator *
 */
public class Orders {	
	private Long uuid;//编号
    private java.util.Date notedate; // 单据日期
    private java.util.Date createtime;//生成日期
	private java.util.Date checktime;//审核日期
	private java.util.Date starttime;//确认日期
	private java.util.Date endtime;//入库或出库日期
	private Integer type;//1:采购 2:销售
	private Long creater;//下单员
    private String createrName;
	private Long checker;//审核员
    private String checkerName;
	private Long starter;//采购员
    private String starterName;
	private Long ender;//库管员
    private String enderName;
	private Long supplieruuid;//供应商或客户
    private String supplierName;
	private Double totalmoney;//合计金额
	private Integer state;//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
	private String waybillsn;//运单号
    private List<Orderdetail> orderDetails; // 订单明细

    public String getCreaterName() {
        return createrName;
    }
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    public String getCheckerName() {
        return checkerName;
    }
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    public String getStarterName() {
        return starterName;
    }
    public void setStarterName(String starterName) {
        this.starterName = starterName;
    }
    public String getEnderName() {
        return enderName;
    }
    public void setEnderName(String enderName) {
        this.enderName = enderName;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
    public Date getNotedate() {
        return notedate;
    }
    public void setNotedate(Date notedate) {
        this.notedate = notedate;
    }
    public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public java.util.Date getChecktime() {		
		return checktime;
	}
	public void setChecktime(java.util.Date checktime) {
		this.checktime = checktime;
	}
	public java.util.Date getStarttime() {		
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {		
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getCreater() {		
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Long getChecker() {		
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	public Long getStarter() {		
		return starter;
	}
	public void setStarter(Long starter) {
		this.starter = starter;
	}
	public Long getEnder() {		
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getSupplieruuid() {		
		return supplieruuid;
	}
	public void setSupplieruuid(Long supplieruuid) {
		this.supplieruuid = supplieruuid;
	}
	public Double getTotalmoney() {		
		return totalmoney;
	}
	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getWaybillsn() {		
		return waybillsn;
	}
	public void setWaybillsn(String waybillsn) {
		this.waybillsn = waybillsn;
	}
    public List<Orderdetail> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<Orderdetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "uuid=" + uuid +
                ", notedate=" + notedate +
                ", createtime=" + createtime +
                ", checktime=" + checktime +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", type=" + type +
                ", creater=" + creater +
                ", createrName='" + createrName + '\'' +
                ", checker=" + checker +
                ", checkerName='" + checkerName + '\'' +
                ", starter=" + starter +
                ", starterName='" + starterName + '\'' +
                ", ender=" + ender +
                ", enderName='" + enderName + '\'' +
                ", supplieruuid=" + supplieruuid +
                ", supplierName='" + supplierName + '\'' +
                ", totalmoney=" + totalmoney +
                ", state=" + state +
                ", waybillsn='" + waybillsn + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
