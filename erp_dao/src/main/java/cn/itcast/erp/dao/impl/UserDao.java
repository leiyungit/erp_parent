package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IUserDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.User;

import java.util.List;

public class UserDao extends BaseDao<User> implements IUserDao {



    /**
     * 登录
     * 其中 from Emp 的Emp是实体名，不是表名
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public boolean signIn(String username, String pwd) {
        List<User> list = (List<User>)this.getHibernateTemplate().find("from Emp where username=? and pwd=?",username,pwd);
        return (list.size() > 0) ? true : false;
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
        return false;
    }
}
