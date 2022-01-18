package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Goodstype;

import java.util.Map;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IGoodstypeBiz extends IBaseBiz<Goodstype>{

    /**
     * 根据类别名称获取类别编号
     * @param name
     * @param goodstypeMap
     * @return
     */
    Long getGoodstypeCode(String name, Map<String,Long> goodstypeMap);

    /**
     * 根据类别编号获取类别名称
     * @param id
     * @param goodstypeMap
     * @return
     */
    String getGoodstypeName(Long id, Map<Long,String> goodstypeMap);
}

