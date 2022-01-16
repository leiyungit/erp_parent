package cn.itcast.erp.biz.enums;

public enum SupplierTypeEnum {
    SUPPLIER(1,"供应商"),
    CUSTOMER(2,"客户"),
    ;

    private Integer code;
    private String message;

    SupplierTypeEnum(Integer code, String message) {
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
