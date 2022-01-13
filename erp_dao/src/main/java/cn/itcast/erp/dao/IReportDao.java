package cn.itcast.erp.dao;

import java.util.Date;
import java.util.List;

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
    List ordersReport(Date startDate,Date endDate);
}
