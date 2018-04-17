package com.xiaobai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * spring boot启动类
 * @author bail
 * @date 2018/1/25.15:16
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xiaobai.*.dao"})
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
