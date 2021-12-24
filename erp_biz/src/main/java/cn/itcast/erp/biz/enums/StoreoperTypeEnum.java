package cn.itcast.erp.biz.enums;

/**
 * 仓库库存变更记录 操作类型
 */
public enum StoreoperTypeEnum {

    IN(1,"入库"),
    OUT(2,"出库"),
    ;

    private Integer code;

    private String message;

    StoreoperTypeEnum(Integer code, String message) {
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
