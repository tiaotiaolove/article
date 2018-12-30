package com.xiaobai.login.service;

import com.xiaobai.common.base.CommonRuntimeException;
import com.xiaobai.common.base.ErrorCode;
import com.xiaobai.goods.dao.IGoodsDao;
import com.xiaobai.goods.entity.Goods;
import com.xiaobai.goods.service.AsyncService;
import com.xiaobai.login.dao.ILoginDao;
import com.xiaobai.login.entity.LoginUser;
import com.xiaobai.login.request.LoginUserReq;
import com.xiaobai.util.HttpUtil;
import com.xiaobai.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

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

    /**
     * 登陆逻辑
     * @param userReq 输入的用户名与密码
     * @return token
     */
    public String login(LoginUserReq userReq) {
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
        return UUID.randomUUID().toString();
    }

    /**
     * hex加密
     *
     * @param b 待加密字节数组
     * @return 加密后的字符串
     */
    private static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();

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
