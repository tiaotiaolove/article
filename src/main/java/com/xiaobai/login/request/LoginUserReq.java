package com.xiaobai.login.request;

import com.xiaobai.common.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登陆用户请求对象
 *
 * @author bail
 * @date 2018/12/30.14:08
 */
@ApiModel
@Data
public class LoginUserReq extends BaseRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @NotBlank
    @ApiModelProperty(value = "账号", required = true, example = "xxx")
    private String account;
    /**
     * 密码
     */
    @NotBlank
    @ApiModelProperty(value = "密码", required = true, example = "xxx")
    private String password;

}
