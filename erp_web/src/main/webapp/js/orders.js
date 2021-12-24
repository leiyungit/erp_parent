

$(function (){
    var url = 'orders_listByPage?t1.type=1'; //  采购
    // 如果审核业务，加上state=0，只查询出未审核的订单
    if(Request['oper'] == 'doCheck'){
        url += "&t1.state=0";
    }
    // 如果确认业务，加上state=1，只查询出已审核的订单
    if(Request['oper'] == 'doStart'){
        url += "&t1.state=1";
    }
    // 如果入库业务，加上state=2，只查询出已确认过的订单
    if(Request['oper'] == 'doInStore'){
        url += "&t1.state=2";
    }
    $('#grid').datagrid({
        url: url,
        dataType:"json",
        type: 'post',
        columns: columns,
        pagination: true, // 在DataGrid控件底部显示分页工具栏
        singleSelect: true, // 只允许选择一行
        rownumbers: true, // 显示一个行号列
        fitColumns: true,
        onDblClickRow: function(rowIndex,rowData){
            // rowIndex：点击的行的索引值，该索引值从0开始
            // rowData：对应于点击行的记录。
            console.log(rowData)
            $('#ordersDlg').dialog('open')
            for (let key in rowData) {
                console.log(key + ":" + rowData[key])
                if(key == 'state'){
                    $('#'+key).html(getOrdersState(rowData[key]));
                }else if(key.indexOf('time')>0){
                    $('#'+key).html(formatDate(rowData[key]));
                }else{
                    $('#'+key).html(rowData[key]);
                }
            }
            console.log(rowData.orderDetails);
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
            {field:'storenum',title: '已入库数量',width: 100}
        ]],
        fitColumns: true,  // 在DataGrid控件底部显示分页工具栏
        singleSelect: true // 只允许选择一行
    });
    // 添加审核按钮
    if(Request['oper'] == 'doCheck'){
        $('#ordersDlg').dialog({
            toolbar: [{
                text: '审核',
                iconCls: 'icon-search',
                handler: doCheck
            }]
        });
    }
    // 添加确认按钮
    if(Request['oper'] == 'doStart'){
        $('#ordersDlg').dialog({
            toolbar: [{
                text: '确认',
                iconCls: 'icon-search',
                handler: doStart
            }]
        });
    }
    // 入库，双击打开入库窗口
    if(Request['oper'] == 'doInStore'){
        $('#itemgrid').datagrid({
            onDblClickRow:function(rowIndex, rowData){
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
    //入库窗口
    $('#itemDlg').dialog({
        width:300,
        height:200,
        title:'入库',
        modal:true,
        closed:true,
        buttons:[
            {
                text:'入库',
                iconCls:'icon-save',
                handler:doInStore
            }
        ]
    });
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
 * 入库
 */
function doInStore(){
    var formdata = $('#itemForm').serializeJSON();
    if(formdata.storeuuid == ''){
        $.messager.alert('提示','请选择仓库!','info');
        return;
    }
    console.log(formdata);
    $.messager.confirm('确认', '确定要入库吗？', function(yes){
        if (yes){
            $.ajax({
                url:'orderdetail_doInStore',
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

var columns = [[
    {field:'uuid',title:'流水号',width:100},
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

