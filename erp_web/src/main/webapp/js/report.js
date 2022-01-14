$(function () {
    $('#grid').datagrid({
        url: 'report_ordersReport',
        columns: [[
            {field:'name',title:'商品类别',width:100},
            {field:'y',title:'销售额',width:100}
        ]],
        onLoadSuccess: function (_data){
            // 当表格数据加载成功时，画图
            showChart(_data.rows);
        }
    });
    // 查询
    $('#btnSearch').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#searchForm').serializeJSON();
        if(formData.endDate){
            // 如果有截止日期，补上当天最后的时间
            formData.endDate += " 23:59:59 999";
        }
        $('#grid').datagrid('load', formData);
    });
    // 饼状图
   function showChart(_data) {
        $('#pieChart').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '销售统计'
            },
            // 信用，显示官方网站
            credits: {enabled:false},
            // 导出
            exporting: {enabled:true},
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: "比例",
                colorByPoint: true,
                data: _data
            }]
        });
    };
});