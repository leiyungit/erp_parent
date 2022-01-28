$(function () {
    $('#tree').tree({
        url: 'emp_readEmpRoles?id=0',
        animate: true,
        checkbox: true
    });

    $('#grid').datagrid({
        url: 'emp_list',
        singleSelect:true,
        columns: [[
            {field: 'uuid', title: '编号', width: 100},
            {field: 'name', title: '名称', width: 100}
        ]],
        onClickRow: function (rowIndex,rowData){
            $('#tree').tree({
                url: 'emp_readEmpRoles?id=' + rowData['uuid'],
                animate: true,
                checkbox: true
            });
        }
    });

    $('#btnSave').bind('click',function (){
        var selectGrid = $('#grid').datagrid('getSelected');
        if(null == selectGrid){
            console.log(selectGrid);
            alertInfo('请选择角色');
            return;
        }
        var checkedStr = new Array();
        var nodes = $('#tree').tree('getChecked');
        $.each(nodes,function (index,node){
            checkedStr.push(node.id);
        })
        var formdata = {};
        formdata.id = $('#grid').datagrid('getSelected').uuid;
        formdata.checkedStr = checkedStr.join(',');
        $.ajax({
            url:'emp_updateEmpRoles',
            data: formdata,
            dataType:'json',
            type:'post',
            success: function (rtn){
                alertInfo(rtn.message);
            }
        });
    });
})