package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Orders;
/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IOrdersBiz extends IBaseBiz<Orders>{
    /**
     * 采购订单审核
     * @param uuid
     * @param empUuid
     */
    void doCheck(Long uuid, Long empUuid);

    /**
     * 采购订单确认
     * @param uuid
     * @param empUuid
     */
    void doStart(Long uuid, Long empUuid);


    // void doInStore();
}

