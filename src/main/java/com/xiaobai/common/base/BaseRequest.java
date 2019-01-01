package com.xiaobai.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求参数基类
 * 用于对入参进行统一校验
 *
 * @author bail
 * @date 2018/12/31.14:53
 */
@Data
public class BaseRequest implements Serializable {
    /**
     * 统一参数校验入口
     */
    public void checkParam() {
    }
}
