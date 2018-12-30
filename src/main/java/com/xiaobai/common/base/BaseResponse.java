package com.xiaobai.common.base;

import com.xiaobai.util.MessageSourceUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Locale;

/**
 * 响应基类
 *
 * @author bail
 * @date 2018/12/25.18:25
 */
@ApiModel(value = "BaseResponse", description = "响应基类")
@Data
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "结果码", required = true)
    private String code;

    @ApiModelProperty(value = "结果码对应的消息内容")
    private String message;

    @ApiModelProperty(value = "返回的业务数据内容")
    private T context;

    private BaseResponse() {
    }

    public BaseResponse(String code) {
        this.code = code;
        this.message = MessageSourceUtil.getMessage(code, null, Locale.CHINA);
    }

    private BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(String code, String message, T context) {
        this.code = code;
        this.message = message;
        this.context = context;
    }

    /**
     * 基础的成功返回数据
     *
     * @return 通用成功码 + 通用成功描述
     */
    public static BaseResponse SUCCESSFUL() {
        return new BaseResponse(ErrorCode.SUCCESSFUL);
    }

    /**
     * 基础的错误返回数据
     *
     * @return 通用错误码 + 通用错误描述
     */
    public static BaseResponse FAILED() {
        return new BaseResponse(ErrorCode.FAILED);
    }

    /**
     * 成功
     *
     * @param context 内容
     */
    public static <T> BaseResponse<T> success(T context) {
        String message = MessageSourceUtil.getMessage(ErrorCode.SUCCESSFUL, null, Locale.CHINA);
        return new BaseResponse<>(ErrorCode.SUCCESSFUL, message, context);
    }

    /**
     * 特殊场景
     *
     * @param message 消息
     */
    public static <T> BaseResponse<T> info(String code, String message) {
        return new BaseResponse<>(code, message);
    }

}
