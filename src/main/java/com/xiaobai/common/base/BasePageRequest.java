package com.xiaobai.common.base;

import lombok.Data;

/**
 * 分页请求数据基类
 *
 * @author bail
 * @date 2018/12/31.14:54
 */
@Data
public class BasePageRequest extends BaseRequest {
    /**
     * 第几页
     */
    private Integer pageNum = 1;

    /**
     * 每页显示多少条
     */
    private Integer pageSize = 10;
}
