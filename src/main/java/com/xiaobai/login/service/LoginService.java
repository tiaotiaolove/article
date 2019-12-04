package com.xiaobai.login.service;

import com.xiaobai.common.base.CommonRuntimeException;
import com.xiaobai.common.base.ErrorCode;
import com.xiaobai.config.JwtProperties;
import com.xiaobai.login.dao.ILoginDao;
import com.xiaobai.login.entity.LoginUser;
import com.xiaobai.login.request.LoginUserReq;
import com.xiaobai.login.response.LoginRes;
import com.xiaobai.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用户登陆Service
 *
 * @author bail
 * @date 2018/12/30.15:39
 */
@Service
@Slf4j
public class LoginService {
    @Autowired
    private ILoginDao loginDao;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登陆逻辑
     *
     * @param userReq 输入的用户名与密码
     * @return token
     */
    public LoginRes login(LoginUserReq userReq) {
        LoginUser user = loginDao.queryOneUser(userReq.getAccount());
        if (user == null) {
            // 用户不存在,即用户名错误
            throw new CommonRuntimeException(ErrorCode.WRONG_LOGIN_ACCOUNT);
        } else {
            // 验证输入的密码是否正确
            String secPassword = toHexString(
                    md5Hex(user.getUserId() + toHexString(md5Hex(userReq.getPassword())) + user.getUserSalt())
            );
            if (!user.getUserPassword().equals(secPassword)) {
                throw new CommonRuntimeException(ErrorCode.WRONG_LOGIN_PASSWORD);
            }
        }
        return LoginUtil.getLoginResponse(user, jwtProperties.getSecretKey());
    }

    /**
     * hex加密
     *
     * @param b 待加密字节数组
     * @return 加密后的字符串
     */
    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < b.length; ++i) {
            sb.append("0123456789abcdef".charAt(b[i] >>> 4 & 15));
            sb.append("0123456789abcdef".charAt(b[i] & 15));
        }

        return sb.toString();
    }

    /**
     * md5加密
     *
     * @param data 待加密的字符串
     * @return 加密后的字节数组
     */
    private static byte[] md5Hex(String data) {
        try {
            return MessageDigest.getInstance("MD5").digest(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
