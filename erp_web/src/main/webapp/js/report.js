$(function () {
    $('#grid').datagrid({
        url: 'report_ordersReport',
        columns: [[
            {field:'name',title:'商品类别',width:100},
            {field:'y',title:'销售额',width:100}
        ]]
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
});