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