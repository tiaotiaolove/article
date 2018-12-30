package com.xiaobai.login;

import com.xiaobai.common.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 登录Controller
 *
 * @author bail
 * @date 2018/12/29.17:35
 */
@Api(description = "管理员登录/登出API")
@RestController()
@RequestMapping("/employee")
public class LoginController {
    /**
     * 密码错误超过5次后锁定的时间，单位：分钟
     */
    private static final int LOCK_MINUTES = 30;

    /**
     * 允许密码错误最大次数
     */
    private static final int PASS_WRONG_MAX_COUNTS = 5;

    /**
     * 管理员登录
     */
    @ApiOperation(value = "管理员登录", notes = "base64编码即可")
    @PostMapping(value = "/login")
    public BaseResponse login() {
//        String account = new String(Base64.getUrlDecoder().decode(loginRequest.getAccount().getBytes()));
//        String password = new String(Base64.getUrlDecoder().decode(loginRequest.getPassword().getBytes()));
//
//        //验证手机号
//        if (ValidateUtil.isPhone(account)) {
//            return employeeService.findByPhone(account, AccountType.s2bBoss)
//                    //逻辑 账号也是手机号，但是跟手机号不一样
//                    .map(employee -> customerValidate(employee, password, response)).orElseGet(() ->
//                            employeeService.findByAccountName(account, AccountType.s2bBoss)
//                                    .map(employee -> customerValidate(employee, password, response))
//                                    .orElseThrow(() -> validateNull(account)));
//        } else {
//            return employeeService.findByAccountName(account, AccountType.s2bBoss)
//                    .map(employee -> customerValidate(employee, password, response))
//                    .orElseThrow(() -> validateNull(account));
//        }
        return BaseResponse.SUCCESSFUL();
    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "管理员登出接口", notes = "管理员退出登录")
    @GetMapping(value = "/loginOut")
    public BaseResponse loginOut() {
        return BaseResponse.SUCCESSFUL();
    }
}
