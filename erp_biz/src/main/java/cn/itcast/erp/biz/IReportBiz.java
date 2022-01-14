package cn.itcast.erp.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReportBiz {
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
