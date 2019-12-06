package com.xiaobai.util;

import com.xiaobai.login.entity.LoginUser;
import com.xiaobai.login.response.LoginRes;
import com.xiaobai.login.response.vo.LoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * BFF 登陆信息工具类
 *
 * @author bail
 * @date 2018/12/31
 */
public final class LoginUtil {

    /**
     * 获取当前登录人id
     */
    public static Long getUserId() {
        return getUser().getUserId();
    }

    /**
     * 获取当前登录对象
     */
    public static LoginUser getUser() {
        Claims claims = (Claims) (HttpUtil.getRequest().getAttribute("claims"));
        LoginUser user = new LoginUser();
        if (claims == null) {
            return user;
        } else {
            user.setUserId(Long.parseLong(claims.getSubject()));
            user.setUserPhone(String.valueOf(claims.get("userPhone")));
        }
        return user;
    }

    /**
     * 拼接登录后返回值
     *
     * @param loginUser
     * @return
     */
    public static LoginRes getLoginResponse(LoginUser loginUser, String jwtSecretKey) {
        Date date = new Date();
        String token = Jwts.builder().setSubject(loginUser.getUserId().toString())
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .setIssuedAt(date)
                .claim("userPhone", loginUser.getUserPhone())
                .claim("ip", HttpUtil.getIpAddr())
                // 1个月过期
                .setExpiration(DateUtils.addMonths(date, 1))
                .compact();

        return LoginRes.builder()
                .loginVO(LoginVO.builder()
                        .userPhone(loginUser.getUserPhone())
                        .userNickName(loginUser.getUserNickName())
                        .build())
                .token(token)
                .build();
    }

}
