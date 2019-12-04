package com.xiaobai.login;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.login.request.LoginUserReq;
import com.xiaobai.login.response.LoginRes;
import com.xiaobai.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Base64;

/**
 * 登录Controller
 *
 * @author bail
 * @date 2018/12/29.17:35
 */
@Api(tags = "LoginController-用户登录/登出API")
@RestController()
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 管理员登录
     */
    @ApiOperation(value = "管理员登录", notes = "base64编码即可")
    @PostMapping(value = "/login")
    public BaseResponse<LoginRes> login(@Valid @RequestBody LoginUserReq loginUserReq) {
        String account = new String(Base64.getUrlDecoder().decode(loginUserReq.getAccount().getBytes()));
        String password = new String(Base64.getUrlDecoder().decode(loginUserReq.getPassword().getBytes()));
        loginUserReq.setAccount(account);
        loginUserReq.setPassword(password);
        return BaseResponse.success(loginService.login(loginUserReq));
    }
}
