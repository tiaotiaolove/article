package com.xiaobai.goods.dao;

import com.xiaobai.goods.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品库存Dao
 *
 * @author bail
 * @date 2018/4/17.15:41
 */
@Repository
public interface IGoodsDao {

    /**
     * 查询List对象
     *
     * @param goods 商品
     * @return 商品List
     */
    List<Goods> queryGoodsList(Goods goods);

    /**
     * 更新对象
     *
     * @param goods 商品
     * @return 更新条数
     */
    int updateGoods(Goods goods);

    /**
     * 插入多个对象
     *
     * @param goodsList 商品List
     * @return 插入条数
     */
    int insertGoodsList(List<Goods> goodsList);

    /**
     * 根据id查询单个对象
     *
     * @param id 商品id
     * @return 商品
     */
    Goods queryGoods(Long id);
}
