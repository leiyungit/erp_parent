package cn.itcast.erp.biz.job;

import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.entity.Storealert;

import javax.mail.MessagingException;
import java.util.List;

/**
 * 后台定时检测库存预警
 * 如果存在库存预警，则发预警邮件给相关工作人员
 */
public class MailJob {

    /**
     * 商品库存业务
     */
    private IStoredetailBiz storedetailBiz;

    public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
        this.storedetailBiz = storedetailBiz;
    }

    public void sendStorealertMail(){
        // 查询是否存在库存预警
        List<Storealert> storealertList = this.storedetailBiz.getStorealertList();
        if(storealertList.size() >0){
            try {
                this.storedetailBiz.sendStoreAlertMail();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }
}
