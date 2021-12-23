package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IUserBiz;
import cn.itcast.erp.biz.exception.ErpException;
import cn.itcast.erp.biz.utils.DataCrypto;
import cn.itcast.erp.dao.IUserDao;
import cn.itcast.erp.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserBiz extends BaseBiz<User> implements IUserBiz {

    private static Logger logger = LoggerFactory.getLogger(UserBiz.class);

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
        System.out.println(DataCrypto.encrypt(pwd,username));
        return userDao.signIn(username, DataCrypto.encrypt(pwd,username));
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

    /**
     * 修改密码
     *
     * @param username
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public boolean updatePwd(String username, String oldPwd, String newPwd) {
        System.out.println("username:"+username+" , oldpwd: "+oldPwd);
        String encrypt = DataCrypto.encrypt(oldPwd, username);
        boolean isLogin = this.signIn(username, oldPwd);
        if(!isLogin){
            logger.error("旧秘密不正确aaa");
            System.out.println(encrypt);
            //logger.error("旧秘密不正确, username:{}, pwd:{}",username,encrypt);
            throw new ErpException("旧秘密不正确");
        }
        return this.userDao.updatePwd(username, DataCrypto.encrypt(newPwd, username));
    }

    /**
     * 重置密码
     *
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public boolean updatePwd_reset(String username, String pwd) {
        return this.userDao.updatePwd(username, DataCrypto.encrypt(pwd, username));
    }

}
