package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoredetailBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Action 
 * @author Administrator
 *
 */
public class StoredetailAction extends BaseAction<Storedetail> {

	private IStoredetailBiz storedetailBiz;
	
	public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
		this.storedetailBiz = storedetailBiz;
		setBaseBiz(storedetailBiz);
	}

    /**
     * 获取库存预警列表
     * @return
     */
    public void storealertList() {
        List<Storealert> storealertList = this.storedetailBiz.getStorealertList();
        ResultUtil.write(JSON.toJSONString(storealertList));
    }

    /**
     *发送预警邮件
     */
    public void sendStorealertMail(){
        try {
            storedetailBiz.sendStoreAlertMail();
            ResultUtil.ajaxReturnSuccess("发送预警邮件成功！");
        } catch (MessagingException e) {
            ResultUtil.ajaxReturnFail("构建预警邮件失败！");
            e.printStackTrace();
        } catch (ErpException e){
            ResultUtil.ajaxReturnFail(e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            ResultUtil.ajaxReturnFail("发送邮件失败！");
            e.printStackTrace();
        }
    }
}
