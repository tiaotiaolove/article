package com.xiaobai.login.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 登陆VO
 *
 * @author bail
 * @date 2018/12/31
 */
@ApiModel
@Data
@Builder
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号", required = true, example = "173")
    private String userPhone;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = true, example = "tiaotiao")
    private String userNickName;
}
