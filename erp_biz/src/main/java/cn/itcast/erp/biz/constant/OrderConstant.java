package cn.itcast.erp.biz.constant;

/**
 * 订单相关的常量
 */
public interface OrderConstant {
    /** 未审核 */
    public static final String STATE_CREATE = "0";
    /** 已审核 */
    public static final String STATE_CHECK = "1";
    /** 已确认 */
    public static final String STATE_START = "2";
    /** 已入库 */
    public static final String STATE_END = "3";
    /** 取消 */
    public static final String STATE_CANCEL = "99";

    /** 采购订单 */
    public static final String TYPE_IN = "1";
    /** 销售订单 */
    public static final String TYPE_OUT = "2";
}
