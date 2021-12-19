package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.dao.IUserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void testDep(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext_*.xml");
        //IUserDao userDao = (IUserDao)ac.getBean("userDao");
        // System.out.println(depDao.getList().size());
        //System.out.println(userDao.signIn("admin","admin"));
        //ac.getBean("sessionFactory");

    }

}