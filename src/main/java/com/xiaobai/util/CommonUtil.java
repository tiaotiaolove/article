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
public final class CommonUtil {

    /**
     * 获取当前登录人id
     */
    public static Integer getUserId() {
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
            user.setUserId(Integer.parseInt(claims.getSubject()));
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
                // 30分钟过期
                .setExpiration(DateUtils.addMinutes(date, 30))
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
