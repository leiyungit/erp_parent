package cn.itcast.erp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表
 */
public interface IReportDao {

    /**
     * 销售统计
     * @param startDate
     * @param endDate
     * @return
     */
    List soOrdersReport(Date startDate, Date endDate);

    /**
     * 销售趋势图
     * @param year 年
     * @return
     */
    List<Map<String,Object>> soTrendReport(int year);
}
