package com.xiaobai.goods;

import com.xiaobai.goods.entity.Goods;
import com.xiaobai.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品库存Controller
 * @author bail
 * @date 2018/1/25.15:16
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping()
    public List<Goods> home(){
        return goodsService.queryGoodsList(new Goods());
    }

    @GetMapping(value = "/{id}")
    public Goods get(@PathVariable String id){
        return goodsService.queryGoods(id);
    }

    @PutMapping()
    public Goods update(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

}
