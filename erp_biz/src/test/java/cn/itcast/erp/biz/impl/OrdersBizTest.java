package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.entity.Orders;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class OrdersBizTest extends TestCase {

    @Test
    public void testListByPage() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext_*.xml");
        IOrdersBiz ordersBiz = (IOrdersBiz)ac.getBean("ordersBiz");
        System.out.println(ordersBiz);
        Orders t1 = new Orders();
        t1.setType(1);
        for (int i = 0; i < 10; i++) {
            List<Orders> orders = ordersBiz.listByPage(t1, null, null, 0, 20);
        }

    }
}