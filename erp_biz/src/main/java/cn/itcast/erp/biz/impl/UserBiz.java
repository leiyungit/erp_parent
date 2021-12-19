package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IUserBiz;
import cn.itcast.erp.dao.IUserDao;
import cn.itcast.erp.entity.User;

public class UserBiz extends BaseBiz<User> implements IUserBiz {

    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
        super.setBaseDao(this.userDao);
    }

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public boolean signIn(String username, String pwd) {
        return userDao.signIn(username,pwd);
    }

    /**
     * 注册
     *
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public boolean signUp(String username, String pwd) {
        return userDao.signUp(username,pwd);
    }
}
