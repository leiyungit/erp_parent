package cn.itcast.erp.dao;

import cn.itcast.erp.entity.User;

public interface IUserDao  extends IBaseDao<User> {


    /**
     * 登录
     * @return
     */
    boolean signIn(String username, String pwd);

    /**
     * 注册
     * @return
     */
    boolean signUp(String username, String pwd);


    /**
     * 修改密码
     * @param username
     * @param pwd
     * @return
     */
    boolean updatePwd(String username, String pwd);
}
