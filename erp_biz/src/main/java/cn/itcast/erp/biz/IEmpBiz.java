package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Emp;

import java.util.Map;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IEmpBiz extends IBaseBiz<Emp>{

    Emp findByUsername(String username);

    /**
     * 根据员工编号获取员工名称
     * @param empUuid
     * @param empMap
     * @return
     */
    String getEmpName(Long empUuid, Map<Long,String> empMap);
}

