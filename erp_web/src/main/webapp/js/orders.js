

$(function (){
    var type = Request['type']*1
    var url = 'orders_listByPage?t1.type=' +type; //  采购
    var btnText = "";
    var inoutTitle = "";
    var toolbar = new Array();
    if(!$('#notedate').datebox('getValue')){
        console.log("单据日期："+$('#notedate').datebox('getValue'))
        $('#notedate').datebox('setValue', formatDate(new Date()));
    }
    console.log("单据日期2："+$('#notedate').datebox('getValue'))
    if(type == 1){
        btnText = '采购申请';
        inoutTitle = "入库";
        document.title="我的采购订单";
        $('.labSupplier').html('供应商：');
    }else if(type == 2){
        btnText = '销售订单录入';
        inoutTitle = "出库";
        document.title="我的销售订单";
        $('.labSupplier').html('客户：');
    }
    if(Request['oper'] == 'myorders'){
        url = 'orders_myListByPage?t1.type=' + Request['type'];
        // 添加表格头部按钮
        $('#grid').datagrid({
            toolbar: [{
                text: btnText,
                iconCls: 'icon-add',
                handler: function (){
                    $('#addOrdersDlg').dialog('open');
                }
            }]
        })
    }
    // 采购订单查询
    if(Request['oper'] == 'orders'){
        document.title="采购订单查询";
    }
    // 如果审核业务，加上state=0，只查询出未审核的订单
    if(Request['oper'] == 'doCheck'){
        url += "&t1.state=0";
        document.title="采购订单审核";
    }
    // 如果确认业务，加上state=1，只查询出已审核的订单
    if(Request['oper'] == 'doStart'){
        url += "&t1.state=1";
        document.title="采购订单确认";
    }
    // 如果入库业务，加上state=2，只查询出已确认过的订单
    if(Request['oper'] == 'doInStore'){
        url += "&t1.state=2";
        document.title="采购订单入库";

    }
    if(Request['oper'] == 'doOutStore'){
        url += "&t1.state=0";
        document.title="销售订单出库";

    }
    $('#grid').datagrid({
        url: url,
        dataType:"json",
        type: 'post',
        columns: getColumns(),
        pagination: true, // 在DataGrid控件底部显示分页工具栏
        singleSelect: true, // 只允许选择一行
        rownumbers: true, // 显示一个行号列
        fitColumns: true,
        onDblClickRow: function(rowIndex,rowData){
            // rowIndex：点击的行的索引值，该索引值从0开始
            // rowData：对应于点击行的记录。
            // console.log(rowData)
            $('#ordersDlg').dialog('open')
            for (var key in rowData) {
                // console.log(key + ":" + rowData[key])
                if(key == 'state'){
                    $('#'+key).html(getOrdersState(rowData[key]));
                }else if(key.indexOf('time')>0){
                    $('#'+key).html(formatDate(rowData[key]));
                }else{
                    $('#'+key).html(rowData[key]);
                }
            }
            // console.log(rowData.orderDetails);
            // 加载明细
            $('#itemgrid').datagrid('loadData',rowData.orderDetails);
        }
    });

    // 明细表格
    $('#itemgrid').datagrid({

        columns: [[
            {field:'uuid',title:'编号',width:100},
            {field:'goodsuuid',title:'商品编号',width:100},
            {field:'goodsname',title:'商品名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'num',title:'数量',width:100},
            {field:'money',title:'金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getOrderDetailState},
            {field:'storenum',title: '已'+inoutTitle+'数量',width: 100}
        ]],
        fitColumns: true,  // 在DataGrid控件底部显示分页工具栏
        singleSelect: true // 只允许选择一行
    });

    // 添加审核按钮
    if(Request['oper'] == 'doCheck'){
        toolbar.push({
            text: '审核',
            iconCls: 'icon-search',
            handler: doCheck
        })
    }
    // 添加确认按钮
    if(Request['oper'] == 'doStart'){
        toolbar.push({
            text: '确认',
            iconCls: 'icon-search',
            handler: doStart
        })
    }
    toolbar.push('-');
    toolbar.push({
        text: '导出',
        iconCls: 'icon-excel',
        handler: doExport
    })
    $('#ordersDlg').dialog({
        toolbar: toolbar
    });
    //  列表双击事件（模态窗口中），入库/出库
    if(Request['oper'] == 'doInStore' || Request['oper'] == 'doOutStore'){
        $('#itemgrid').datagrid({
            onDblClickRow:function(rowIndex, rowData){
                // todo: 如果采购订单是全部已入库，销售订单是全部已出库，则不加载模态窗口
                if(Request['type']*1 == 1 && rowData.state*1 == 2){
                    console.log('采购明细已全部入库，无须操作入库')
                    return;
                }
                if(Request['type']*1 == 2 && rowData.state*1 == 2){
                    console.log('销售明细已全部出库，无须操作出库')
                    return;
                }
                //显示数据
                $('#itemuuid').val(rowData.uuid);
                $('#goodsuuid').html(rowData.goodsuuid);
                $('#goodsname').html(rowData.goodsname);
                var num = parseFloat((rowData.num-(rowData.storenum==null?0:rowData.storenum)).toFixed(2));
                $('#goodsnum').val(num);
                //打开入库窗口
                $('#itemDlg').dialog('open');

            }
        });
    }
    // 入库/出库窗口
    $('#itemDlg').dialog({
        title:inoutTitle,
        width:300,
        height:200,
        modal:true,
        closed:true,
        buttons:[
            {
                text:inoutTitle,
                iconCls:'icon-save',
                handler:doInOutStore
            }
        ]
    });
    // 采购申请窗口
    //var _content="<iframe scrolling='auto' frameborder='0' src='../erp/orders_add.html?type="+type+"' style='width:100%; height:100%; display:block;'></iframe>";
    $('#addOrdersDlg').dialog({
        title:'添加订单',
        width:700,
        height:400,
        modal:true,
        closed:true,
        //content:_content
    })
})

/**
 * 审核
 */
function doCheck(){
    $.messager.confirm('确认', '确定要审核吗？', function(yes){
        if (yes){
            $.ajax({
                url:'orders_doCheck?id='+$('#uuid').html(),
                dataType:"json",
                type: 'post',
                success: function (rtn){
                    if(rtn.success){
                        //关闭窗口
                        $('#ordersDlg').dialog('close');
                        //刷新表格
                        $('#grid').datagrid('reload');
                    }else{
                        $.messager.alert('提示',rtn.message,'info')
                    }
                }
            });
        }
    });
}
/**
 * 确认
 */
function doStart(){
    $.messager.confirm('确认', '确定要确认吗？', function(yes){
        if (yes){
            $.ajax({
                url:'orders_doStart?id='+$('#uuid').html(),
                dataType:"json",
                type: 'post',
                success: function (rtn){
                    if(rtn.success){
                        //关闭窗口
                        $('#ordersDlg').dialog('close');
                        //刷新表格
                        $('#grid').datagrid('reload');
                    }else{
                        $.messager.alert('提示',rtn.message,'info')
                    }
                }
            });
        }
    });
}
/**
 * 出入库
 */
function doInOutStore(){
    var message = '';
    var url = '';
    if(Request['type']*1 == 1){
        message = '确定要入库吗?';
        url = 'orderdetail_doInStore';
    }
    if(Request['type']*1 == 2){
        message = '确定要出库吗?';
        url = 'orderdetail_doOutStore'
    }
    var formdata = $('#itemForm').serializeJSON();
    if(formdata.storeuuid == ''){
        $.messager.alert('提示','请选择仓库!','info');
        return;
    }
    console.log(formdata);
    $.messager.confirm('确认', message, function(yes){
        if (yes){
            $.ajax({
                url: url,
                data: formdata,
                dataType:"json",
                type: 'post',
                success: function (rtn){
                    if(rtn.success){
                        //关闭窗口
                        $('#itemDlg').dialog('close');
                        $('#ordersDlg').dialog('close');
                        //刷新表格
                        $('#grid').datagrid('reload');
                    }else{
                        $.messager.alert('提示',rtn.message,'info')
                    }
                }
            });
        }
    });
}

/**
 * 导出
 */
function doExport(){
    $.download("orders_export",{"id":$('#uuid').html()});
}
/**
 * 根据订单类型，获取不同的列
 */
function getColumns(){
    if(Request['type'] * 1 == 1){
        return [[
            {field:'uuid',title:'编号',width:100},
            {field:'notedate',title:'单据日期',width:100,formatter:formatDate},
            {field:'checktime',title:'审核日期',width:100,formatter:formatDate},
            {field:'starttime',title:'确认日期',width:100,formatter:formatDate},
            {field:'endtime',title:'入库日期',width:100,formatter:formatDate},
            {field:'createrName',title:'下单员',width:100},
            {field:'checkerName',title:'审核员',width:100},
            {field:'starterName',title:'采购员',width:100},
            {field:'enderName',title:'库管员',width:100},
            {field:'supplierName',title:'供应商',width:100},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getOrdersState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }
    if(Request['type'] * 1 == 2){
        return [[
            {field:'uuid',title:'编号',width:100},
            {field:'notedate',title:'单据日期',width:100,formatter:formatDate},
            {field:'endtime',title:'出库日期',width:100,formatter:formatDate},
            {field:'createrName',title:'下单员',width:100},
            {field:'enderName',title:'库管员',width:100},
            {field:'supplierName',title:'客户',width:100},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getOrdersState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }
}
var columns = [[
    {field:'uuid',title:'编号',width:100},
    {field:'createtime',title:'生成日期',width:100,formatter:formatDate},
    {field:'checktime',title:'审核日期',width:100,formatter:formatDate},
    {field:'starttime',title:'确认日期',width:100,formatter:formatDate},
    {field:'endtime',title:'入库日期',width:100,formatter:formatDate},
    {field:'createrName',title:'下单员',width:100},
    {field:'checkerName',title:'审核员',width:100},
    {field:'starterName',title:'采购员',width:100},
    {field:'enderName',title:'库管员',width:100},
    {field:'supplierName',title:'供应商',width:100},
    {field:'totalmoney',title:'合计金额',width:100},
    {field:'state',title:'状态',width:100,formatter:getOrdersState}, // 采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
    {field:'waybillsn',title:'运单号',width:100},
]];

