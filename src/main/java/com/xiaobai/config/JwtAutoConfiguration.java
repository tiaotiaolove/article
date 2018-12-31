package com.xiaobai.config;

import com.xiaobai.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JWT认证自动配置类
 * 注解@Order 是为了优先级空100个位置出来, 满足业务在JWT认证之后添加拦截器处理
 * @author bail
 * @date 2018/12/31.9:17
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "jwt", name = "secretKey")
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class JwtAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String secretKey = jwtProperties.getSecretKey();
        String jwtHeaderKey = jwtProperties.getJwtHeaderKey();
        String jwtHeaderPrefix = jwtProperties.getJwtHeaderPrefix();
        String[] urlPatterns = jwtProperties.getUrlPatterns();
        String[] excludeUrls = jwtProperties.getExcludedUrls();
        String excludedRestUrls = jwtProperties.getExcludedRestUrls();
        registry.addWebRequestInterceptor(
                new JwtInterceptor(secretKey, jwtHeaderKey, jwtHeaderPrefix, excludedRestUrls))
                .addPathPatterns(urlPatterns)
                .excludePathPatterns(excludeUrls);
    }
}
