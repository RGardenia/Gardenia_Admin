package com.zy.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zhangyu
 */
@MapperScan("com.zy.service.mapper")
@SpringBootApplication
public class ServiceMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMainApplication.class, args);
    }
}