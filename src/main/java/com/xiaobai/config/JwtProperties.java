package com.xiaobai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt 配置
 *
 * @author bail
 * @date 2018/12/31.9:17
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    /**
     * 过滤器路径
     */
    private String[] urlPatterns = {};

    /**
     * 排除的url列表；被排除的url将不参与jwt验证；
     */
    private String[] excludedUrls = {};

    /**
     * 排除的restful url Map；被排除的url将不参与jwt验证；
     * 因为存在只开放GET的查询接口,不开放POST,PUT等操作接口的场景
     */
    private String excludedRestUrls;

    /**
     * 私钥信息
     */
    private String secretKey = "defaultKey";

    /**
     * 获取jwt信息请求头header中的key
     */
    private String jwtHeaderKey = "Authorization";

    /**
     * jwt信息的前缀
     */
    private String jwtHeaderPrefix = "Bearer ";
}
