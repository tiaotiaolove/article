package com.xiaobai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域 configuration
 *
 * @author bail
 * @date 2018/12/30.20:17
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //设置允许的方法
                .allowedMethods("*")
                //设置允许的请求头
                .allowedHeaders("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true);
    }
}
