var method = '';
var name = '';
var rownumbers = false; // 显示一个行号列
var columns = [];
var width = 300;
var height = 200;
var listParam = ""; // 供应商 或 客户 类型，列表参数
var saveParam = "";  // 供应商 或 客户 类型，保存参数
var addBtnText = '新增';
var addIcon = 'icon-add';
var toolbar = [{
    text: '新增',
    iconCls: 'icon-add',
    handler: function () {
        method = 'add';
        $('#editForm').form('clear');
        $('#editDlg').dialog('open');
    }
}];
$(function () {
    //$.messager.alert("提示","欢迎来到....");
    $('#grid').datagrid({
        url: name + '_listByPage'+listParam,
        columns: columns,
        pagination: true, // 在DataGrid控件底部显示分页工具栏
        singleSelect: true, // 只允许选择一行
        rownumbers: rownumbers, // 显示一个行号列
        toolbar: toolbar
    });
    // 查询
    $('#btnSearch').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#searchForm').serializeJSON();
        $('#grid').datagrid('load', formData);
        // 把json对象转换成字符串
        // alert(JSON.stringify(formData))

        /*  原生
        $.ajax({
             url: name+'_list',
             data: formData,
             dataType: 'json',
             type: 'post',
             success:function(rtn){
                 //grid加载数据
                 //datagrid(参数1，参数2)
                 //参数1 =》 datagrid里的方法名称
                 //参数2 =》 datagrid里的方法所需要的参数
                 $('#grid').datagrid('loadData',rtn);
                 //
                 total = rtn.length;
                 {total: total, rows:rtn};
             }
         });*/
    });

    $('#editDlg').dialog({
        title: '编辑',
        width: width,
        height: height,
        closed: true, // 默认打开或关闭，true:关闭
        modal: true
    });
    // 导入
    if($('#importDlg')){
        $('#importDlg').dialog({
            title: '导入数据',
            width: 360,
            height: 110,
            closed: true, // 默认打开或关闭，true:关闭
            modal: true,
            buttons:[
                {
                    text:'导入',
                    iconCls:'icon-save',
                    handler:function (){
                        $.ajax({
                            url: name+'_doImport',
                            type: 'post',
                            dataType:'json',
                            data: new FormData($('#importForm')[0]),
                            processData: false, // 要求为Boolean类型的参数，默认为true。默认情况下，发送的数据将被转换为对象（从技术角度来讲关非字符串）以配合默认内容类型”application/x-www-form-urlencoded”。如果要发送DOM树信息或者其它不希望转换的信息，请设置为false
                            contentType: false, // 要求为String类型的参数，当发送信息至服务器时，内容编码类型默认为”application/x-www-form-urlencoded”。该默认值适合大多数应用场合
                            success: function (rtn){
                                console.log(rtn)
                                alertInfo(rtn.message);
                                if(rtn.success){
                                    // 关闭窗口
                                    $('#importDlg').dialog('close');
                                    // 刷新列表
                                    $('#grid').datagrid('reload');
                                    $('#importDlg').find('[name=file]').val('');
                                }
                            }
                        })
                    }
                }
            ]
        });
    }
    // 保存，新增/编辑
    $('#btnSave').bind('click', function () {
        //把表单数据转换成json对象
        var formData = $('#editForm').serializeJSON();

        $.ajax({
            url: name + '_' + method + saveParam,
            data: formData,
            dataType: 'json',
            type: 'post',
            success: function (rtn) {
                $.messager.alert('提示', rtn.message, 'info', function () {
                    // 成功的话，我们要关闭窗口
                    $('#editDlg').dialog('close');
                    // 刷新表格数据
                    $('#grid').datagrid('reload');
                });
            }
        });
    }); // btnsave

});

/**
 * 删除
 */
function del(uuid) {
    console.log(uuid);
    $.messager.confirm('确认', '确认要删除吗？', function (yes) {
        if (yes) {
            $.ajax({
                url: name + '_delete?id=' + uuid,
                dataType: 'json',
                type: 'post',
                success: function (rtn) {
                    $.messager.alert("提示", rtn.message, 'info', function () {
                        // 刷新表格数据
                        $('#grid').datagrid('reload');
                    });
                }
            });
        }
    });
}

/**
 * 修改
 */
function edit(uuid) {
    //弹出窗口
    $('#editDlg').dialog('open');

    //清空表单内容
    $('#editForm').form('clear');
    method = "update";
    //加载数据
    $('#editForm').form('load', name + '_get?id=' + uuid);
}