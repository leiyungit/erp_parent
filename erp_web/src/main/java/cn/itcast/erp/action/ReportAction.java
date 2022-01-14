package cn.itcast.erp.action;

import cn.itcast.erp.biz.IReportBiz;
import cn.itcast.erp.utils.ResultUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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
        List list = this.reportBiz.soOrdersReport(startDate, endDate);
        ResultUtil.write(JSON.toJSONString(list));
    }

    public void soTrendReport(){
        List<Map<String, Object>> list = this.reportBiz.soTrendReport(year);
        ResultUtil.write(JSON.toJSONString(list));
    }
}
