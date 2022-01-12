var existEditIndex=-1;
$(function (){
    if(Request['type']*1 == 1){
        $('#addOrdersSupplier').html('供应商')
    }else if(Request['type']*1 == 2){
        $('#addOrdersSupplier').html('客户')
    }
    $('#ordersgrid').datagrid({
        columns: [[
            {field:'goodsuuid',title:'商品编号',width:100,editor:{type:'numberbox',options:{disabled:true}}}, // 禁止编辑
            {field:'goodsname',title:'商品名称',width:100,editor:{type:'combobox',options: {
                url:'goods_list',panelWidth: 500,valueField:'name',textField:'name',panelWidth: 200,
                        panelHeight: 'auto',
                        formatter: formatItemGoods,
                        onSelect:function (goods){
                            // 列中必须声明editor
                            var goodsuuidEditor = getEditor('goodsuuid');
                            $(goodsuuidEditor.target).val(goods.uuid);
                            // 价格
                            var priceEditor = getEditor('price');
                            $(priceEditor.target).val(goods.inprice);
                            // 数量
                            var numEditor = getEditor('num');
                            //选中数量输入框
                            $(numEditor.target).select();
                            // 绑定键盘事件
                            bindGridEditor();
                            cal();
                            // $('#ordersgrid').datagrid('getRows')[existEditIndex].price = goods.inprice;
                        }
                    }}},
            {field:'price',title:'价格',width:100,editor:{type:'numberbox',options:{precision:2}}},
            {field:'num',title:'数量',width:100,editor:{type:'numberbox',options:{precision:2}}},
            {field:'money',title:'金额',width:100,editor:{type:'numberbox',options:{disabled:true,precision:2}}},
            {field: '-',title: '操作',width:100, align: 'center',formatter: function(value,row,rowIndex){
                    if(row.num != '合计'){
                        return "<a href='javascript:void(0)' onclick='deleteRow("+rowIndex+")'>删除</a>";
                    }
                }}
        ]],
        singleSelect: true, // 只允许选择一行
        rownumbers: true, // 显示一个行号列
        showFooter: true,
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                //判断是否存在编辑的行
                if(existEditIndex>-1){
                    //关闭编辑
                    $('#ordersgrid').datagrid('endEdit',existEditIndex);
                }
                //增加一行, row参数:{goodsuuid:'',goodsname:'',price:''}
                $('#ordersgrid').datagrid('appendRow',{money: 0, num: 1});
                //获取所的行记录，数组
                var rows = $('#ordersgrid').datagrid('getRows');
                //设置当前编辑行的索引
                existEditIndex = rows.length - 1;
                //需要先设置它的编辑器，才能开启编辑状态
                $('#ordersgrid').datagrid('beginEdit',existEditIndex);
                // 绑定键盘事件
                /*bindGridEditor();
                cal();*/
            }},'-',{
                text: '提交',
                iconCls: 'icon-save',
                handler: function(){
                    // 1. 存在编辑状态的行
                    if(existEditIndex > -1){
                        $('#ordersgrid').datagrid('endEdit',existEditIndex);
                    }
                    var formData = $('#orderForm').serializeJSON();
                    // console.log(formData['t.supplieruuid']);
                    // 没有选择供应商，退出
                    if(!formData['t.supplieruuid'] || formData['t.supplieruuid'] == '请选择'){
                        $.messager.alert('提示','请先选择'+$('#addOrdersSupplier').html(),'info');
                        return ;
                    }
                    // 获取所有的明细
                    var rows = $('#ordersgrid').datagrid('getRows');
                    if(rows.length == 0){
                        $.messager.alert('提示','请先添加商品项','info');
                        return ;
                    }
                    formData.json = JSON.stringify(rows);
                    $.ajax({
                        url: 'orders_add?t.type=' + Request['type'],
                        dataType:"json",
                        data: formData,
                        type: 'post',
                        success: function (rtn){
                            if(rtn.success){
                                // 清空供应商
                                // $('#supplier').combogrid('clear');
                                // 刷新表格和行脚
                                $('#ordersgrid').datagrid('loadData',{total:0,rows:[],footer:[{num: '合计', money: 0}]});
                                // 关闭添加订单的模态窗口+刷新列表
                                $('#addOrdersDlg').dialog('close');
                                // 刷新订单列表
                                $('#grid').datagrid('reload');
                                $.messager.alert('提示','添加订单成功','info')
                            }else{
                                $.messager.alert('提示',rtn.message,'info')
                            }
                            console.log(rtn);
                        }
                    });

            }}],
        onClickRow:function (rowIndex, rowData){
            //rowIndex：点击的行的索引值，该索引值从0开始。
            //rowData：对应于点击行的记录。
            //关闭当前可以编辑的行
            if(existEditIndex>-1){
                $('#ordersgrid').datagrid('endEdit',existEditIndex);
            }
            //设置当前可编辑的索引行
            existEditIndex = rowIndex;
            $('#ordersgrid').datagrid('beginEdit',existEditIndex);
            bindGridEditor();
            cal();
        }
    });
    // 添加行脚
    $('#ordersgrid').datagrid('reloadFooter',[{num: '合计', money: 0}]);
    // 供应商表格下拉绑定
    $('#supplier').combogrid({
        panelWidth:650,
        value:'请选择',
        idField:'uuid',
        textField:'name',
        url:'supplier_list?t1.type='+Request['type']*1,
        columns:[[
            {field:'uuid',title:'编号',width:80},
            {field:'name',title:'名称',width:100},
            {field:'address',title:'联系地址',width:150},
            {field:'contact',title:'联系人',width:100},
            {field:'tele',title:'联系电话',width:100},
            {field:'email',title:'邮件地址',width:100},
        ]],
        mode: 'remote'
    });
})

/**
 * 获取指定编辑器
 * @param filedName
 * @returns {jQuery}
 */
function getEditor(filedName){
    // index：行索引。
    // field：字段名称。
    return $('#ordersgrid').datagrid('getEditor', {index:existEditIndex,field:filedName});
}

/**
 * 商品下拉 自定义组合显示
 * @param row
 * @returns {string}
 */
function formatItemGoods(row){
    var s = '<span style="font-weight:bold">' + row.name + '</span><br/>' +
        '<span style="color:#888;padding-left: 10px;">' + row.producer + '</span>' +
        '<span style="color:#888;padding-left: 10px;">' + row.inprice +'/'+row.unit+ '</span>';
    return s;
}

/**
 * 绑定表格编辑的键盘输入事件
 */
function bindGridEditor(){
    // 数量
    var numEditor = getEditor('num');
    $(numEditor.target).bind('keyup',function (){
        cal();
    });
    // 进货价格
    var priceEditor = getEditor('price');
    $(priceEditor.target).bind('keyup',function (){
        cal();
    });
}

/**
 * 小计
 */
function cal(){
    console.log('cal')
    // 获取进货价格
    var priceEditor = getEditor('price');
    var price = $(priceEditor.target).val();
    // 获取数量
    var numEditor = getEditor('num');
    var num = $(numEditor.target).val();
    // 计算金额
    var money = (price * num).toFixed(2);
    // 设置金额
    var moneyEditor = getEditor('money');
    $(moneyEditor.target).val(money);
    // 更新表格中的数据,设置row json对象里的key对应的值
    $('#ordersgrid').datagrid('getRows')[existEditIndex].money = money;
    // 合计
    sum();
}

/**
 * 合计
 */
function sum(){
    // 所有的表格
    var rows = $('#ordersgrid').datagrid('getRows');
    // 合计
    var total = 0;
    $.each(rows,function (index,row){
        total += parseFloat(row.money);
    });
    //设置合计金额到行脚里去
    $('#ordersgrid').datagrid('reloadFooter',[{num: '合计', money: total.toFixed(2)}]);
}

function deleteRow(rowIndex){
    console.log(rowIndex)
    // 关闭编辑行，否则正在编辑的商品信息会丢失
    $('#ordersgrid').datagrid('endEdit',existEditIndex);
    // 删除
    $('#ordersgrid').datagrid('deleteRow',rowIndex);
    // 获取删除后的数据
    var data = $('#ordersgrid').datagrid('getData');
    // 重新加载
    $('#ordersgrid').datagrid('loadData',data)
    // 重新计算合计
    sum();
}