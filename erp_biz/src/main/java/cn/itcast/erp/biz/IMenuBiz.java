package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Menu;

import java.util.List;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IMenuBiz extends IBaseBiz<Menu>{

    /**
     * 根据员工编号获取菜单
     * @param empUuid 员工编号
     * @return
     */
    Menu readMenusByEmpuuid(Long empUuid);
}

