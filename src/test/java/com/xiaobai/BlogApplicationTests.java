package com.xiaobai;

import com.xiaobai.goods.entity.Goods;
import com.xiaobai.goods.service.GoodsService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * JUnit单元测试
 * BeforeClass
 *
 * Before
 * Test
 * After
 *
 *  Before
 * Test
 * After
 *
 * AfterClass
 *
 * @author bail
 * @date 2018/6/10.9:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void queryGoodsListTest() {
        List<Goods> list = goodsService.queryGoodsList(new Goods());
        Assert.assertNotNull(list);
    }

    @Test
    public void queryGoodsTest() {
        Goods goods = goodsService.queryGoods(1L);
        Assert.assertNotNull(goods.getStock());
    }

    @Test(expected = NullPointerException.class)
    public void updateGoodsTest() {
        Goods param = new Goods();
        param.setGoodsInfoId(6L);
        param.setStock(99L);
        goodsService.updateGoods(param);
    }

    @Test
    public void insertGoodsListTest() {
        List<Goods> goodsList = new ArrayList<>();
        Goods param = new Goods();
        param.setGoodsInfoId(12L);
        param.setStock(1L);
        goodsList.add(param);
        int count = goodsService.insertGoodsList(goodsList);
        Assert.assertEquals(goodsList.size(), count);
    }

}
