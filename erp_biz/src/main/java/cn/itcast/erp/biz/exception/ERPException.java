package cn.itcast.erp.biz.exception;

/**
 * 自定义异常，用于逻辑抛出
 */
public class ERPException extends RuntimeException {

    public ERPException(String message){
        super(message);
    }
}
