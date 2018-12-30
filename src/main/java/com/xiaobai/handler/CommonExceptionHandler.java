package com.xiaobai.handler;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.common.base.CommonRuntimeException;
import com.xiaobai.common.base.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.Locale;

/**
 * 异常统一处理
 *
 * @author bail
 * @date 2018/12/30.10:00
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private static final String LOGGER_FORMAT = "【通用业务操作执行异常】：异常编码{},异常描述：{},堆栈信息如下：";

    /**
     * 业务异常处理
     *
     * @param ex 业务异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(CommonRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponse commonRuntimeExceptionHandler(CommonRuntimeException ex) {
        String errorCode = ex.getErrorCode();
        String msg = this.getMessage(errorCode, ex.getParams());
        log.error(LOGGER_FORMAT, ex.getErrorCode(), msg, ex);
        return BaseResponse.info(errorCode, msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse validationExceptionHandle(MethodArgumentNotValidException ex) {
        log.error("400, Bad Request, MethodArgumentNotValidException=>{}", ex.getMessage());
        return new BaseResponse(ErrorCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse constraintViolationExceptionHandle(ConstraintViolationException ex) {
        final StringBuilder sb = new StringBuilder();
        ex.getConstraintViolations().forEach(
                i -> sb
                        .append(i.getRootBeanClass().getName())
                        .append(".")
                        .append(i.getPropertyPath())
                        .append(i.getMessage()).append("\r\n")
        );
        log.error("400, Bad Request, ConstraintViolationException=>{}", sb);
        return new BaseResponse(ErrorCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse illegalStateExceptionHandle(IllegalStateException ex) {
        log.error("400, Bad Request, IllegalStateException=>{}", ex.getMessage());
        return new BaseResponse(ErrorCode.PARAMETER_ERROR);
    }

    /**
     * 非业务通用异常的处理(代码运行时,未捕获的异常)
     *
     * @param ex 异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponse defaultExceptionHandler(Throwable ex) {
        String msg = ex.getMessage();
        log.error(LOGGER_FORMAT, "defaultException", msg, ex);
        return BaseResponse.FAILED();
    }

    /**
     * 根据错误码 获取 错误描述
     *
     * @param code   错误码
     * @param params 错误描述中的动态参数
     * @return 完整的错误描述
     */
    private String getMessage(String code, Object[] params) {
        try {
            return messageSource.getMessage(code, params, Locale.CHINA);
        } catch (NoSuchMessageException e) {
            return code;
        }
    }
}
