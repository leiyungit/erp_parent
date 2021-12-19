package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Emp;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface IEmpDao extends IBaseDao<Emp>{

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    Emp findByUsername(String username);
}
