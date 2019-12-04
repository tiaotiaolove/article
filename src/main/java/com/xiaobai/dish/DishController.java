package com.xiaobai.dish;

import com.github.pagehelper.PageInfo;
import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.dish.request.DishAddReq;
import com.xiaobai.dish.request.DishDelReq;
import com.xiaobai.dish.request.DishQueryReq;
import com.xiaobai.dish.request.DishUpdateReq;
import com.xiaobai.dish.response.DishRes;
import com.xiaobai.dish.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "DishController-菜品查询API")
@RestController()
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;

    @ApiOperation(value = "获取菜品分页列表", notes = "默认按照主键id倒序排列")
    @PostMapping("/page")
    public BaseResponse<PageInfo<DishRes>> dishPage(@RequestBody @Valid DishQueryReq dishQueryReq) {
        return BaseResponse.success(dishService.queryDishPage(dishQueryReq));
    }

    @ApiOperation(value = "添加菜品", notes = "...")
    @PostMapping("/add")
    public BaseResponse addDish(@RequestBody @Valid DishAddReq dishAddReq) {
        dishService.addDish(dishAddReq);
        return BaseResponse.SUCCESSFUL();
    }

    @ApiOperation(value = "编辑菜品", notes = "...")
    @PostMapping("/update")
    public BaseResponse updateDish(@RequestBody @Valid DishUpdateReq dishUpdateReq) {
        dishService.updateDish(dishUpdateReq);
        return BaseResponse.SUCCESSFUL();
    }

    @ApiOperation(value = "删除菜品", notes = "...")
    @PostMapping("/delete")
    public BaseResponse deleteDish(@RequestBody @Valid DishDelReq dishDelReq) {
        dishService.deleteDish(dishDelReq);
        return BaseResponse.SUCCESSFUL();
    }
}
