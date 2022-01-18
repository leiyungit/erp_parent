package cn.itcast.erp.biz;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Supplier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 业务逻辑层接口
 * @author Administrator
 *
 */
public interface IGoodsBiz extends IBaseBiz<Goods>{
    /**
     * 导出到excel文件
     * @param os 输出流
     * @param t1 查询条件
     */
    void export(OutputStream os, Goods t1) throws IOException;

    /**
     * excel导入
     * @param is
     * @throws IOException
     */
    void doImport(InputStream is)throws IOException;

    /**
     *
     * @param id
     * @param goodsMap
     * @return
     */
    String getGoodsName(Long id, Map<Long,String> goodsMap);
}

