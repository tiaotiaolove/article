package com.xiaobai.common.base;

/**
 * <p>公共异常码定义</p>
 *
 * @author bail
 * @date 2018/12/25.18:25
 */
public final class ErrorCode {
    /*=======================================通用错误码===============================================*/
    /**
     * 指定异常，不走国际化
     */
    public final static String SPECIFIED = "K-999999";

    /**
     * 操作成功
     */
    public final static String SUCCESSFUL = "K-000000";

    /**
     * 操作失败
     */
    public final static String FAILED = "K-000001";

    /**
     * 没有访问功能的权限
     */
    public final static String METHOD_NOT_ALLOWED = "K-000002";

    /**
     * 参数错误
     */
    public final static String PARAMETER_ERROR = "K-000003";

    /**
     * 重复提交
     */
    public final static String REPEAT_REQUEST = "K-000004";

    /**
     * 操作频繁
     */
    public final static String FREQUENT_OPERATION = "K-000005";

    /**
     * 输入内容包含非法字符
     */
    public final static String ILLEGAL_CHARACTER = "K-000006";

    /**
     * Missing Token
     */
    public final static String MISSING_TOKEN = "K-000007";

    /**
     * Invalid Token
     */
    public final static String INVALID_TOKEN = "K-000008";

    /**
     * Expired Token
     */
    public final static String EXPIRED_TOKEN = "K-000009";

    /*=======================================管理员错误码===============================================*/
    /**
     * 账号不存在
     */
    public final static String WRONG_LOGIN_ACCOUNT = "K-010001";

    /**
     * 密码错误
     */
    public final static String WRONG_LOGIN_PASSWORD = "K-010002";

    private ErrorCode() {
    }
}
