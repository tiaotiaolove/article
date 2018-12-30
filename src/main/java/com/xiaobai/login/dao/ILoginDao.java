package com.xiaobai.login.dao;

import com.xiaobai.login.entity.LoginUser;
import org.springframework.stereotype.Repository;

/**
 * 商品库存Dao
 *
 * @author bail
 * @date 2018/12/30.15:41
 */
@Repository
public interface ILoginDao {

    /**
     * 根据手机号查询用户信息
     *
     * @param phone 用户手机号
     * @return 用户信息
     */
    LoginUser queryOneUser(String phone);
}
