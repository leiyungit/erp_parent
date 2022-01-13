package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IReportBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.dao.IReportDao;

import java.util.Date;
import java.util.List;

public class ReportBiz implements IReportBiz {

    /** 数据访问注入*/
    private IReportDao reportDao;

    public void setReportDao(IReportDao reportDao) {
        this.reportDao = reportDao;
    }

    /**
     * 销售统计
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List ordersReport(Date startDate, Date endDate) {
        return this.reportDao.ordersReport(startDate,endDate);
    }
}
