package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IReportDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportDao extends HibernateDaoSupport implements IReportDao {
    /**
     * 销售统计
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List soOrdersReport(Date startDate, Date endDate) {
        String hql = "select new Map(gt.name as name, sum(ol.money) as y) "
                + "from Goodstype gt, Goods gs, Orderdetail ol, Orders o "
                + "where gs.goodstype=gt and ol.orders=o and ol.goodsuuid=gs.uuid "
                + "and o.type=2 ";
        List<Date> dates = new ArrayList<>();
        // todo: 这里不应该用创建日期，而应该添加单据日期
        if(null != startDate){
            hql += "and o.notedate>=? ";
            dates.add(startDate);
        }
        if(null != endDate){
            hql += "and o.notedate<=? ";
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

    /**
     * 销售趋势图
     *
     * @param year 年
     * @return
     */
    @Override
    public List<Map<String, Object>> soTrendReport(int year) {
        String hql = "SELECT new Map(MONTH(o.notedate) as name, SUM(ol.money) as y)  FROM Orders o,Orderdetail ol WHERE ol.orders=o AND o.type=2 " +
                "AND YEAR(o.notedate)=? " +
                "GROUP BY MONTH(o.notedate) ";
        return (List<Map<String, Object>>) this.getHibernateTemplate().find(hql, year);
    }
}
