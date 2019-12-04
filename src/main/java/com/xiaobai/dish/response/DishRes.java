package com.xiaobai.dish.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品响应类
 *
 * @author bail
 * @date 2018/12/31.14:08
 */
@ApiModel
@Data
public class DishRes implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜品id
     */
    @ApiModelProperty(value = "菜品id", required = true, example = "1")
    private Long dishId;

    /**
     * 适合季节(0,1,2,3分别代表春夏秋冬)
     */
    @ApiModelProperty(value = "适合季节(0,1,2,3分别代表春夏秋冬)", required = true, example = "0")
    private Integer season;

    /**
     * 油腻程度(0:纯素 1:小荤 2:大荤)
     */
    @ApiModelProperty(value = "油腻程度(0:纯素 1:小荤 2:大荤)", required = true, example = "0")
    private Integer oilyDegree;

    /**
     * 菜品种类(0.凉菜类 1:炒菜类 2:红烧类 3:汤类 4.果盘类)
     */
    @ApiModelProperty(value = "菜品种类(0.凉菜类 1:炒菜类 2:红烧类 3:汤类 4.果盘类)", required = true, example = "0")
    private Integer dishType;

    /**
     * 菜品名称
     */
    @ApiModelProperty(value = "菜品名称", required = true, example = "蚂蚁上树")
    private String dishName;

    /**
     * 菜品图片
     */
    @ApiModelProperty(value = "菜品图片", example = "xxx")
    private String dishPic;

    /**
     * 菜品需要的素材
     */
    @ApiModelProperty(value = "菜品需要的素材", example = "粉丝一小把,芝麻些许...")
    private String dishSource;

    /**
     * 菜品描述
     */
    @ApiModelProperty(value = "菜品描述", example = "好吃的不得了")
    private String dishDesc;

    /**
     * 热度(回头率)
     */
    @ApiModelProperty(value = "热度(回头率)", required = true, example = "0")
    private Integer hotGrade;

    /**
     * 是否被删除(0:未删除 1:已删除)
     */
    @ApiModelProperty(value = "是否被删除(0:未删除 1:已删除)", required = true, example = "0")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true, example = "2019-12-04 13:21:00")
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id", required = true, example = "1")
    private Integer createUserId;
}
