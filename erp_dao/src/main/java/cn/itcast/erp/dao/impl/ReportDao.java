package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IReportDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportDao extends HibernateDaoSupport implements IReportDao {
    /**
     * 销售统计
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List ordersReport(Date startDate, Date endDate) {
        String hql = "select new Map(gt.name as name, sum(ol.money) as y) "
                + "from Goodstype gt, Goods gs, Orderdetail ol, Orders o "
                + "where gs.goodstype=gt and ol.orders=o and ol.goodsuuid=gs.uuid "
                + "and o.type=2 ";
        List<Date> dates = new ArrayList<>();
        // todo: 这里不应该用创建日期，而应该添加单据日期
        if(null != startDate){
            hql += "and o.createtime>=? ";
            dates.add(startDate);
        }
        if(null != endDate){
            hql += "and o.createtime<=? ";
            dates.add(endDate);
        }
        hql += "group by gt.name ";
        Date[] array = dates.toArray(new Date[]{});
        System.out.println("遍历日期，size："+array.length);
        for (Date date : array) {
            System.out.println(date);
        }

        return this.getHibernateTemplate().find(hql, dates.toArray(new Date[]{}));
    }
}
