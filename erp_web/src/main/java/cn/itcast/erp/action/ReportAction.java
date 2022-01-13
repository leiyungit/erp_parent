package cn.itcast.erp.action;

import cn.itcast.erp.biz.IReportBiz;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;

/**
 * 报表统计
 */
public class ReportAction {
    private IReportBiz reportBiz;

    public void setReportBiz(IReportBiz reportBiz) {
        this.reportBiz = reportBiz;
    }

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void ordersReport(){
        List list = this.reportBiz.ordersReport(startDate, endDate);
        ResultUtil.write(JSON.toJSONString(list));
    }
}
