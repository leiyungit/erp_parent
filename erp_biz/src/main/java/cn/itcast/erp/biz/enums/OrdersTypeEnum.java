package cn.itcast.erp.biz.enums;

/**
 * 订单类型
 */
public enum OrdersTypeEnum {

    PO(1,"采购订单"),
    SO(2,"销售订单"),
    ;

    private Integer code;

    private String message;

    OrdersTypeEnum(Integer code, String message) {
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
