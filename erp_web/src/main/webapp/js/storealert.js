$(function () {
    //$.messager.alert("提示","欢迎来到....");
    $('#grid').datagrid({
        url: 'storedetail_storealertList',
        columns: [[
            {field:'uuid',title:'商品编号',width:100},
            {field:'name',title:'商品名称',width:100},
            {field:'storenum',title:'库存数量',width:100},
            {field:'innum',title:'待入库数量',width:100},
            {field:'outnum',title:'待发货数量',width:100}
        ]],
        singleSelect: true, // 只允许选择一行
        rownumbers: true // 显示一个行号列
    });
    // 查询
    /*$('#btnSearch').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#searchForm').serializeJSON();
        $('#grid').datagrid('load', formData);
    });*/
});