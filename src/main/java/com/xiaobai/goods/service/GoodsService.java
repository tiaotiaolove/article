package com.xiaobai.goods.service;

import com.xiaobai.goods.dao.IGoodsDao;
import com.xiaobai.goods.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品库存Service
 * @author bail
 * @date 2018/4/17.15:39
 */
@Service
@Slf4j
public class GoodsService {
    @Autowired
    private IGoodsDao goodsDao;

    public List<Goods> queryGoodsList(Goods goods){
        return goodsDao.queryGoodsList(goods);
    }

    public Goods queryGoods(String id){
        return goodsDao.queryGoods(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Goods updateGoods(Goods goods){
        //提前查一次,测试后面的查询是否走缓存
        Goods before = goodsDao.queryGoods(goods.getGoodsInfoId());
        log.info("before---{}",before);

        //更新库存
        log.info("本次更新的条数:{}",Integer.toString(goodsDao.updateGoods(goods)));

        //测试是否能够查询到修改后的库存
        Goods after = goodsDao.queryGoods(goods.getGoodsInfoId());
        log.info("after---{}",after);

        //测试是否回滚
        String a = null;
        a.toLowerCase();

        return after;
    }

}
