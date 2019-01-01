package com.xiaobai.dish;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.dish.request.DishReq;
import com.xiaobai.dish.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 菜品Controller
 *
 * @author bail
 * @date 2018/12/31
 */
@Api(tags = "DishController", description = "菜品查询API")
@RestController()
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;

    @ApiOperation(value = "获取菜品分页列表", notes = "默认按照创建时间倒序排列")
    @PostMapping()
    public BaseResponse dishPage(@RequestBody @Valid @ApiParam(value = "菜品Req") DishReq dishReq) {
        return BaseResponse.success(dishService.queryDishPage(dishReq));
    }
}
