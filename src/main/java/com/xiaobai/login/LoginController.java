package com.xiaobai.login;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.login.request.LoginUserReq;
import com.xiaobai.login.response.LoginRes;
import com.xiaobai.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;

/**
 * 登录Controller
 *
 * @author bail
 * @date 2018/12/29.17:35
 */
@Api(tags = "LoginController", description = "用户登录/登出API")
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
    public BaseResponse<LoginRes> login(@Valid @RequestBody
                                  @ApiParam(value = "登陆用户Req", required = true) LoginUserReq loginUserReq) {
        String account = new String(Base64.getUrlDecoder().decode(loginUserReq.getAccount().getBytes()));
        String password = new String(Base64.getUrlDecoder().decode(loginUserReq.getPassword().getBytes()));
        loginUserReq.setAccount(account);
        loginUserReq.setPassword(password);
        return BaseResponse.success(loginService.login(loginUserReq));
    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "用户登出接口", notes = "用户退出登录")
    @GetMapping(value = "/loginOut")
    public BaseResponse loginOut() {
        return BaseResponse.SUCCESSFUL();
    }
}
