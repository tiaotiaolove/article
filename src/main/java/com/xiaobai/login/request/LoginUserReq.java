package com.xiaobai.login.request;

import com.xiaobai.common.base.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登陆用户请求对象
 *
 * @author bail
 * @date 2018/12/30.14:08
 */
@Data
public class LoginUserReq extends BaseRequest {
    /**
     * 账号
     */
    @NotBlank
    private String account;
    /**
     * 密码
     */
    @NotBlank
    private String password;

}
