package com.xiaobai;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * spring boot启动类
 *
 * @author bail
 * @date 2018/1/25.15:16
 */
@Slf4j
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {"com.xiaobai.*.dao"})
public class BlogApplication {

    public static void main(String[] args) throws UnknownHostException {
        Environment env = SpringApplication.run(BlogApplication.class, args).getEnvironment();
        String port = env.getProperty("server.port", "8088");
        String healthPort = env.getProperty("management.port", "9001");

        log.info("Access URLs:\n----------------------------------------------------------\n"
                        + "Local: \t\thttp://localhost:{}\n"
                        + "External: \thttp://{}:{}\n"
                        + "health: \thttp://localhost:{}/actuator/health\n"
                        + "Swagger: \thttp://localhost:{}/swagger-ui.html\n"
                        + "----------------------------------------------------------",
                port,
                InetAddress.getLocalHost().getHostAddress(),
                port,
                healthPort,
                port
        );
    }
}
