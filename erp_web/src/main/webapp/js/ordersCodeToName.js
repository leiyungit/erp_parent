/**
 * 订单类型和状态的编码转换
 */

/**
 * 采购订单状态转换
 * @param value
 * @returns {string|*}
 */
function getOrdersState(value){
    switch (value*1){
        case 0:return '未审核';
        case 1:return '已审核';
        case 2:return '已确认';
        case 3:return '已入库';
        default: return value;
    }
}

/**
 * 采购。获取订单明细的状态
 * @param value
 * @returns {string|*}
 */
function getOrderDetailState(value){
    switch (value*1){
        case 0:return '未入库';
        case 1:return '入库中';
        case 2:return '已入库';
        default: return value;
    }
}