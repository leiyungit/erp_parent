/**
 *自己扩展easyui的 combobox，使其能够有全选和反选的功能。
 **/
var AllCheckCombobox = function (selectId, url,valueField,textField) {
    // options = JSON.parse(options);
    $.ajax({
        url: url,
        dataType: "json",
        type: 'post',
        success: function (rtn) {
            //console.log(rtn);
            var data = [];
            if (rtn.length > 0) {
                for (let i = 0; i < rtn.length; i++) {
                    var row = rtn[i];
                    // console.log(row)
                    var op = {'valueField': row[valueField], 'textField': row[textField]};
                    // console.log(op)
                    data.push(op);
                }
                //console.log(data)
            }
            data.unshift({"textField": "全选", "valueField": ""});
            $('#' + selectId).combobox({
                valueField: 'valueField',
                textField: 'textField',
                editable: false,
                panelHeight: 'auto',
                data: data,
            });
            return data;
        }
    });
};