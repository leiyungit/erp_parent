package cn.itcast.erp.biz.enums;

/**
 * 订单状态
 */
public enum OrdersStateEnum {

    NEW(0,"未审核"),
    CHECK(1,"已审核"),
    START(2,"已确认"),
    END(3,"已入库"),
    CANCLE(99,"已取消"),
    NOT_OUT(0,"未出库"),
    OUT(1,"已出库"),
    ;

    private Integer code;

    private String message;

    OrdersStateEnum(Integer code, String message) {
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
