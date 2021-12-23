package cn.itcast.erp.biz.enums;

public enum OrderDetailStateEnum {

    // Integer state;//采购：0=未入库，1=已入库  销售：0=未出库，1=已出库
    PO_NOT_IN(0,"未入库"),
    PO_IN(1,"已入库"),
    SO_NOT_OUT(0,"未出库"),
    SO_OUT(1,"已出库"),
    ;

    private Integer code;

    private String message;


    OrderDetailStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
