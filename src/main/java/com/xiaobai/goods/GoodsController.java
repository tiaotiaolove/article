package com.xiaobai.goods;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.goods.entity.Goods;
import com.xiaobai.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品库存Controller
 *
 * @author bail
 * @date 2018/1/25.15:16
 */
@Api(tags = "GoodsController", description = "商品管理API")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "获取商品列表", notes = "获取商品列表的前十条")
    @GetMapping()
    public BaseResponse<List<Goods>> goodsList() {
        return BaseResponse.success(goodsService.queryGoodsList(new Goods()));
    }

    @ApiOperation(value = "获取单个商品", notes = "根据商品id获取某个商品信息")
    @GetMapping(value = "/{id}")
    public BaseResponse<Goods> get(@PathVariable @ApiParam(value = "商品id", required = true) Long id) {
        return BaseResponse.success(goodsService.queryGoods(id));
    }

    @ApiOperation(value = "更新商品库存1", notes = "更新商品库存,使用mysql innodb 的行锁防止超卖")
    @PutMapping()
    public BaseResponse update(@Valid @RequestBody @ApiParam(value = "商品Req", required = true) Goods goods) {
        goodsService.updateGoods(goods);
        return BaseResponse.SUCCESSFUL();
    }

    @ApiOperation(value = "更新商品库存2", notes = "使用分布式锁进行更新商品库存,防止超卖")
    @PutMapping(value = "/locked")
    public BaseResponse updateLocked(@RequestBody @ApiParam(value = "商品Req", required = true) Goods goods) {
        goodsService.updateGoodsLocked(goods);
        return BaseResponse.SUCCESSFUL();
    }

    @ApiOperation(value = "插入多个商品", notes = "批量插入商品,单个插入也使用该API")
    @PostMapping()
    public BaseResponse insertList(@RequestBody @ApiParam(value = "商品List", required = true) List<Goods> goodsList) {
        goodsService.insertGoodsList(goodsList);
        return BaseResponse.SUCCESSFUL();
    }

}
