package com.xiaobai.dish.request;

import com.xiaobai.common.base.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜品查询类
 *
 * @author bail
 * @date 2018/12/31.16:43
 */
@ApiModel
@Data
public class DishQueryReq extends BasePageRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 适合季节(0,1,2,3分别代表春夏秋冬)
     */
    @ApiModelProperty(value = "适合季节(0,1,2,3分别代表春夏秋冬)", example = "0")
    private Integer season;

    /**
     * 油腻程度(0:纯素 1:小荤 2:大荤)
     */
    @ApiModelProperty(value = "油腻程度(0:纯素 1:小荤 2:大荤)", example = "0")
    private Integer oilyDegree;

    /**
     * 菜品种类(0.凉菜类 1:炒菜类 2:红烧类 3:汤类 4.果盘类)
     */
    @ApiModelProperty(value = "菜品种类(0.凉菜类 1:炒菜类 2:红烧类 3:汤类 4.果盘类)", example = "0")
    private Integer dishType;

    /**
     * 菜品名称
     */
    @ApiModelProperty(value = "菜品名称", example = "蚂蚁上树")
    private String dishName;

}

