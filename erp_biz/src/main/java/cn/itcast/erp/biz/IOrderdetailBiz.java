package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Orderdetail;
/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IOrderdetailBiz extends IBaseBiz<Orderdetail>{

    /**
     * 采购入库
     * @param uuid 采购明细编号
     * @param storeUuid 仓库编号
     * @param storeNum 入库数量
     * @param empUuid 库管员编号
     * @return 是否成功
     */
    boolean doInStore(Long uuid,Long storeUuid,Double storeNum,Long empUuid);

    /**
     * 采购入库-撤销
     * @param uuid 采购明细编号
     * @param storeUuid 仓库编号
     * @param storeNum 入库数量
     * @param empUuid 库管员编号
     * @return 是否成功
     */
    boolean doNotInStore(Long uuid,Long storeUuid,Double storeNum,Long empUuid);
}

