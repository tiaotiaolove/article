package com.xiaobai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 出于安全考虑: 仅在本机开发环境下启用 SwaggerUI, 在生产环境中禁用
 *
 * @author bail
 * @date 2018/6/7.15:58
 */
@Profile({"local", "dev", "develop", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准文章平台")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaobai"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("article项目的API文档")
                .description("简单优雅的Restful风格，https://github.com/tiaotiaolove")
                .termsOfServiceUrl("https://github.com/tiaotiaolove")
                .version("1.0")
                .build();
    }
}