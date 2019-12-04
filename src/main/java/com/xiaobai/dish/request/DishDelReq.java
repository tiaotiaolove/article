package com.xiaobai.dish.request;

import com.xiaobai.common.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 菜品删除请求参数
 *
 * @author bail
 * @date 2018/12/31.16:43
 */
@ApiModel
@Data
public class DishDelReq extends BaseRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 菜品id
     */
    @ApiModelProperty(value = "菜品id", required = true, example = "1")
    @NotNull
    private Long dishId;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id", hidden = true)
    private Long createUserId;
}

