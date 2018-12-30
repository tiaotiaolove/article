package com.xiaobai.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务自定义的 运行时异常
 *
 * @author bail
 * @date 2018/12/30.10:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonRuntimeException extends RuntimeException {
    /**
     * 错误码
     */
    private String errorCode = ErrorCode.FAILED;
    /**
     * 错误描述中需要的动态参数
     */
    private Object[] params;

    /**
     * 默认构造，展示系统异常
     */
    public CommonRuntimeException() {
        super();
    }

    /**
     * 只有错误码信息
     * 多用于系统自身发生的异常，错误码的错误描述由messageSource读取
     *
     * @param errorCode 错误码
     */
    public CommonRuntimeException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    /**
     * 错误码 + 错误描述信息中需要的动态参数
     * 多用于开发人员手动抛出异常，并传入错误描述信息中需要的动态参数
     *
     * @param errorCode
     * @param params
     */
    public CommonRuntimeException(String errorCode, Object[] params) {
        super(errorCode);
        this.errorCode = errorCode;
        this.params = params;
    }

    /**
     * 错误码 + 上级异常
     *
     * @param errorCode
     * @param cause
     */
    public CommonRuntimeException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }
}
