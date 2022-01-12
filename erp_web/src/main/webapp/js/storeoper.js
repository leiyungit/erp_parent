$(function () {
    //$.messager.alert("提示","欢迎来到....");
    $('#grid').datagrid({
        url: 'storeoper_listByPage'+listParam,
        columns: [[
            {field:'uuid',title:'编号',width:100},
            {field:'storeName',title:'仓库',width:100},
            {field:'goodsName',title:'商品',width:100},
            {field:'num',title:'数量',width:100},
            {field:'type',title:'操作类型',width:100, formatter: function (value){
                    switch (value * 1){
                        case 1: return '入库';
                        case 2: return '出库';
                        default : return value;
                    }
                }},
            {field:'operdesc',title:'业务类型',width:100},
            {field:'empName',title:'操作员',width:100},
            {field:'opertime',title:'操作日期',width:130, formatter: formatDateTime}
        ]],
        pagination: true, // 在DataGrid控件底部显示分页工具栏
        singleSelect: true, // 只允许选择一行
        rownumbers: true // 显示一个行号列
    });
    // 查询
    $('#btnSearch').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#searchForm').serializeJSON();
        $('#grid').datagrid('load', formData);
        // 把json对象转换成字符串
        // alert(JSON.stringify(formData))
    });
});