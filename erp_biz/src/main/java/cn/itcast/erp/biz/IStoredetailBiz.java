package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;

import javax.mail.MessagingException;
import java.util.List;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IStoredetailBiz extends IBaseBiz<Storedetail>{

    /**
     * 获取库存预警列表
     * @return
     */
    List<Storealert> getStorealertList();

    /**
     * 发送库存预警邮件
     */
    void sendStoreAlertMail() throws MessagingException;
}

