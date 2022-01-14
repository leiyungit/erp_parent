
function alertInfo(message) {
    $.messager.alert('提示',message,'info');
}

var yearData = new Array();
function getComboYear(){
    if(yearData.length == 0){
        console.log("加载下拉年")
        var curreYear = new Date().getFullYear();
        for (let i = -1; i < 5; i++) {
            var data = {"year":(curreYear-i)}
            if(i == 0){
                data = {"year":(curreYear-i),"selected":true}

            }
            yearData.push(data);
        }
    }
    return yearData;
}
