package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IReportBiz;
import cn.itcast.erp.dao.IReportDao;

import java.util.*;

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
    public List soOrdersReport(Date startDate, Date endDate) {
        return this.reportDao.soOrdersReport(startDate,endDate);
    }

    /**
     * 销售趋势图
     *
     * @param year 年
     * @return
     */
    @Override
    public List<Map<String, Object>> soTrendReport(int year) {
        List<Map<String, Object>> source = this.reportDao.soTrendReport(year);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String,Map<String, Object>> yearDataMap = new HashMap<>();
        for (Map<String, Object> monthMoney : source) {
            yearDataMap.put(String.valueOf(monthMoney.get("name")),monthMoney);
        }
        Map<String, Object> monthMoney = null;
        for (int i = 1; i < 13; i++) {
            monthMoney = yearDataMap.get(String.valueOf(i));
            if(null == monthMoney){
                monthMoney = new HashMap<>();
                monthMoney.put("name", i+"月");
                monthMoney.put("y", 0);
            }else{
                monthMoney.put("name", monthMoney.get("name")+"月");
            }
            result.add(monthMoney);
        }
        return result;
    }
}
