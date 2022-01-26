package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Orders;
import cn.itcast.redsun.ws.Waybilldetail;

import java.io.OutputStream;
import java.util.List;

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

    /**
     * 导出订单为excel文件
     * @param os
     * @param uuid 订单编号
     */
    void exportById(OutputStream os, Long uuid);

    /**
     * 根据运单号查询运单详情
     * @param sn
     * @return
     */
    List<Waybilldetail> waybilldetailList(Long sn);
    // void doInStore();

}

