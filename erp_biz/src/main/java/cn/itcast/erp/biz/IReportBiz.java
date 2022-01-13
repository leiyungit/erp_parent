package cn.itcast.erp.biz;

import java.util.Date;
import java.util.List;

public interface IReportBiz {
    /**
     * 销售统计
     * @param startDate
     * @param endDate
     * @return
     */
    List ordersReport(Date startDate, Date endDate);
}
