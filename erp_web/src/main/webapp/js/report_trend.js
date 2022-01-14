$(function () {
    console.log(getComboYear())
    $('#year').combobox({
        valueField: 'year',textField: 'year',data:getComboYear()
    })
    // $('#year').combobox('setValue',new Date().getFullYear())
    $('#grid').datagrid({
        queryParams:{},
        columns: [[
            {field:'name',title:'月份',width:100},
            {field:'y',title:'销售额',width:100}
        ]],
        onLoadSuccess: function (_data){
            // 当表格数据加载成功时，画图 _data.rows
            showChart();
        }
    });
    // 查询
    $('#btnSearch').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#searchForm').serializeJSON();
        $('#grid').datagrid({
            url: 'report_soTrendReport',
            queryParams: formData
        });
    });

});

// 饼状图
function showChart() {
    var monthData = new Array();
    for (let i = 1; i < 13; i++) {
        monthData.push(i + '月');
    }
    $('#trendChart').highcharts({
        title: {
            text: $('#year').combobox('getValue') + '年销售趋势分析',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: monthData
        },
        yAxis: {
            title: {
                text: '销售额 (元)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        // 信用，显示官方网站
        credits: {enabled:false},
        // 导出
        exporting: {enabled:true},
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'center',
            verticalAlign: 'bottom',
            borderWidth: 0
        },
        series: [{
            name: '全部商品',
            data: $('#grid').datagrid('getRows')
        }/*, {
            name: 'New York',
            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
        }, {
            name: 'Berlin',
            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
        }, {
            name: 'London',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }*/]
    });
};