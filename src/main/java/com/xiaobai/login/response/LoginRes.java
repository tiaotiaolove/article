package com.xiaobai.login.response;

import com.xiaobai.login.response.vo.LoginVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 登陆返回类
 *
 * @author bail
 * @date 2018/12/31
 */
@ApiModel
@Data
@Builder
public class LoginRes implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 登陆VO
     */
    @ApiModelProperty(value = "账号", required = true)
    private LoginVO loginVO;
    /**
     * jwt token
     */
    @ApiModelProperty(value = "jwt token", required = true, example = "xxx")
    private String token;
}
