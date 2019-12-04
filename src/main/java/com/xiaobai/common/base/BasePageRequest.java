package com.xiaobai.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求数据基类
 *
 * @author bail
 * @date 2018/12/31.14:54
 */
@ApiModel
@Data
public class BasePageRequest extends BaseRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 第几页
     */
    @ApiModelProperty(value = "第几页", required = true, example = "1")
    private Integer pageNum = 1;

    /**
     * 每页显示多少条
     */
    @ApiModelProperty(value = "每页显示多少条", required = true, example = "10")
    private Integer pageSize = 10;
}
