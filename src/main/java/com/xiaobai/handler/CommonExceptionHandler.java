package com.xiaobai.handler;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.common.base.CommonRuntimeException;
import com.xiaobai.common.base.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Locale;
import java.util.stream.Collectors;

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
    public BaseResponse myBusinessExceptionHandler(CommonRuntimeException ex) {
        String errorCode = ex.getErrorCode();
        String msg = StringUtils.isEmpty(ex.getErrorMessage()) ? this.getMessage(errorCode, ex.getParams()) : ex.getErrorMessage();
        log.error(LOGGER_FORMAT, ex.getErrorCode(), msg, ex);
        return BaseResponse.info(errorCode, msg);
    }

    /**
     * 400参数异常(1)
     * 在Controller多个散开的入参@RequestParam(required = true)的场景
     *
     * @param ex 参数异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        String msg = this.getMessage(ErrorCode.PARAMETER_ERROR, null);
        log.error(LOGGER_FORMAT, ErrorCode.PARAMETER_ERROR, "400, Bad Request, MissingServletRequestParameterException=>", new CommonRuntimeException(ErrorCode.PARAMETER_ERROR, ex));
        return BaseResponse.info(ErrorCode.PARAMETER_ERROR, String.format("%s,%s必传", msg, ex.getParameterName()));
    }

    /**
     * 400参数异常(2)
     * 在Controller中@RequestParam(value = "file") MultipartFile file入参未传值的场景
     *
     * @param ex 参数异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse multipartExceptionHandler(MultipartException ex) {
        String msg = this.getMessage(ErrorCode.PARAMETER_ERROR, null);
        log.error(LOGGER_FORMAT, ErrorCode.PARAMETER_ERROR, "400, Bad Request, MultipartException=>", new CommonRuntimeException(ErrorCode.PARAMETER_ERROR, ex));
        return BaseResponse.info(ErrorCode.PARAMETER_ERROR, String.format("%s,请选择上传的文件", msg));
    }

    /**
     * 400参数异常(3)
     * 在Controller多个散开的入参前面加类似@Max(value = 5)的场景
     *
     * @param ex 参数异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse constraintViolationExceptionHandler(ConstraintViolationException ex) {
        String errMsg = ex.getConstraintViolations().stream().map(err -> {
            Iterator i = err.getPropertyPath().iterator();
            String errName = "";
            while (i.hasNext()) {
                errName = i.next().toString();
            }
            return errName + " " + err.getMessage();
        }).collect(Collectors.joining(","));
        String msg = this.getMessage(ErrorCode.PARAMETER_ERROR, null);
        log.error(LOGGER_FORMAT, ErrorCode.PARAMETER_ERROR, "400, Bad Request, ConstraintViolationException=>" + errMsg, new CommonRuntimeException(ErrorCode.PARAMETER_ERROR, ex));
        return BaseResponse.info(ErrorCode.PARAMETER_ERROR, String.format("%s,%s", msg, errMsg));
    }

    /**
     * 400参数异常(4)
     * 在Controller单个@RequestBody @Valid入参内部属性加类似@Max(value = 5)的场景
     *
     * @param ex 参数异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String msg = this.getMessage(ErrorCode.PARAMETER_ERROR, null);
        log.error(LOGGER_FORMAT, ErrorCode.PARAMETER_ERROR, "400, Bad Request, MethodArgumentNotValidException", new CommonRuntimeException(ErrorCode.PARAMETER_ERROR, ex));
        String errorMsg = ex.getBindingResult().getAllErrors().stream().map(err -> {
            String retMsg = err.getDefaultMessage();
            if (err instanceof FieldError) {
                retMsg = ((FieldError) err).getField() + " " + retMsg;
            }
            return retMsg;
        }).collect(Collectors.joining(","));
        return BaseResponse.info(ErrorCode.PARAMETER_ERROR, String.format("%s,%s", msg, errorMsg));
    }

    /**
     * 400参数异常(5)
     * 在Controller单个@RequestBody,却没有传入格式正确的json字符串{}
     *
     * @param ex 参数异常
     * @return 基类数据格式的返回对象
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        String msg = this.getMessage(ErrorCode.PARAMETER_ERROR, null);
        log.error(LOGGER_FORMAT, ErrorCode.PARAMETER_ERROR, "400, Bad Request, HttpMessageNotReadableException=>", new CommonRuntimeException(ErrorCode.PARAMETER_ERROR, ex));
        return BaseResponse.info(ErrorCode.PARAMETER_ERROR, String.format("%s,请传入正确格式的json参数", msg));
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
        log.error(LOGGER_FORMAT, "defaultException", ex.getClass().getSimpleName() + "=>", ex);
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
            log.error("通过errorCode获取message出错 ===> ", e);
            return code;
        }
    }
}
