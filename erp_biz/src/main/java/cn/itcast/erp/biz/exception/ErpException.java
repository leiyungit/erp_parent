package cn.itcast.erp.biz.exception;

/**
 * 自定义异常，用于逻辑抛出
 */
public class ErpException extends RuntimeException {

    public ErpException(String message){
        super(message);
    }
}
