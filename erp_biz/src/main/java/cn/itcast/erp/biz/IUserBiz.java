package cn.itcast.erp.biz;

import cn.itcast.erp.entity.User;

public interface IUserBiz extends IBaseBiz<User> {

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
     * @param oldPwd
     * @param newPwd
     * @return
     */
    boolean updatePwd(String username, String oldPwd, String newPwd);

    /**
     * 重置密码
     * @param username
     * @param pwd
     * @return
     */
    boolean updatePwd_reset(String username, String pwd);
}
