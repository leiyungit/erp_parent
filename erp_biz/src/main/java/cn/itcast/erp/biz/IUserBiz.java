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
}
