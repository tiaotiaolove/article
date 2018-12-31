package com.xiaobai.login.response.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 登陆VO
 * @author bail
 * @date 2018/12/31
 */
@Data
@Builder
public class LoginVO {
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 用户昵称
     */
    private String userNickName;
}
