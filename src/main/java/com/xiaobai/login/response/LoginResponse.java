package com.xiaobai.login.response;

import com.xiaobai.login.response.vo.LoginVO;
import lombok.Builder;
import lombok.Data;

/**
 * 登陆返回类
 *
 * @author bail
 * @date 2018/12/31
 */
@Data
@Builder
public class LoginResponse {
    /**
     * 登陆VO
     */
    private LoginVO loginVO;
    /**
     * jwt token
     */
    private String token;
}
