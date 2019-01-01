package com.xiaobai.dish.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品
 *
 * @author bail
 * @date 2018/12/31.14:08
 */
@Data
public class Dish implements Serializable {
    /**
     * 菜品id
     */
    private Integer dishId;
    /**
     * 适合季节(0,1,2,3分别代表春夏秋冬)
     */
    private Integer season;
    /**
     * 油腻程度(0:纯素 1:小荤 2:大荤)
     */
    private Integer oilyDegree;
    /**
     * 菜品种类(0.凉菜类 1:炒菜类 2:红烧类 3:汤类 4.果盘类)
     */
    private Integer dishType;
    /**
     * 菜品名称
     */
    private String dishName;
    /**
     * 菜品图片
     */
    private String dishPic;
    /**
     * 菜品需要的素材
     */
    private String dishSource;
    /**
     * 菜品描述
     */
    private String dishDesc;
    /**
     * 热度(回头率)
     */
    private Integer hotGrade;
    /**
     * 是否被删除(0:未删除 1:已删除)
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人id
     */
    private Integer createUserId;
}
