package com.xiaobai.login.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author bail
 * @date 2018/12/30.14:08
 */
@Data
public class LoginUser implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 用户昵称
     */
    private String userNickName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 密码盐值
     */
    private String userSalt;
    /**
     * 是否被删除(0:未删除 1:已删除)
     */
    private Integer delFlag;

}
