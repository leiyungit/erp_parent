package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Goods;

import java.util.Map;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IGoodsBiz extends IBaseBiz<Goods>{

    String getGoodsName(Long id, Map<Long,String> goodsMap);
}

