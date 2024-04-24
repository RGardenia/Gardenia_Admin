package com.zy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/24 0:50
 * @Version 1.0
 */

@Configuration
public class WebConfiguration {

    //配置底层
//    @Bean
    public WebFluxConfigurer webFluxConfigurer() {

        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("localhost");
            }
        };
    }


}
