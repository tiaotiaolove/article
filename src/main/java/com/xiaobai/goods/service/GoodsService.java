package com.xiaobai.goods.service;

import com.xiaobai.goods.dao.IGoodsDao;
import com.xiaobai.goods.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品库存Service
 *
 * @author bail
 * @date 2018/4/17.15:39
 */
@Service
@Slf4j
public class GoodsService {
    @Autowired
    private IGoodsDao goodsDao;

    @Autowired
    private RedissonClient redissonClient;

    @Transactional(rollbackFor = Exception.class)
    public List<Goods> queryGoodsList(Goods goods) {
        return goodsDao.queryGoodsList(goods);
    }

    @Transactional(rollbackFor = Exception.class)
    public Goods queryGoods(Long id) {
        return goodsDao.queryGoods(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Goods updateGoodsLocked(Goods goods) {
        Goods after;
        RLock rLock = redissonClient.getFairLock(String.format("lock_%s", goods.getGoodsInfoId().toString()));
        rLock.lock();
        try {
            after = goodsDao.queryGoods(goods.getGoodsInfoId());
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            rLock.unlock();
        }
        return after;
    }

    @Transactional(rollbackFor = Exception.class)
    public Goods updateGoods(Goods goods) {
        //提前查一次,测试后面的查询是否走缓存
        Goods before = goodsDao.queryGoods(goods.getGoodsInfoId());
        log.info("before---{}", before);

        //更新库存-使用mysql innodb 默认的行锁保证不超卖
        log.info("本次更新的条数:{}", Integer.toString(goodsDao.updateGoods(goods)));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //测试是否能够查询到修改后的库存
        Goods after = goodsDao.queryGoods(goods.getGoodsInfoId());
        log.info("after---{}", after);

        //测试是否回滚
        String a = null;
        a.toLowerCase();
        return after;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertGoodsList(List<Goods> goodsList) {
        int count = goodsDao.insertGoodsList(goodsList);
        log.info("本次插入的条数:{}", Integer.toString(count));
        return count;
    }

}
