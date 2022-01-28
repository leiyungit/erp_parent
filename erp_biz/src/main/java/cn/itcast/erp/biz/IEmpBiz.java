package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Tree;

import java.util.List;
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

    /**
     * 读取角色组
     * @param empUuid 员工Id
     * @return
     */
    List<Tree> readEmpRoles(Long empUuid);

    /**
     * 更新角色组
     * @param empUuid 员工Id
     * @param checkedStr 角色Id,多个值用逗号连接，如 1,2,3
     */
    void updateEmpRoles(Long empUuid, String checkedStr);
}

