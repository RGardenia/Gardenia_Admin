package com.zy.actuator.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author zyGardenia - 栀
 * @Since 2022/4/11 19:00
 */

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfigure {
    private final String adminContextPath;

    public SecurityConfigure(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    /**
     * 安全权限配置
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter( "redirectTo" );

        return httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(adminContextPath + "/assets/**").permitAll()      // 获取白名单（不进行权限验证）
                        .requestMatchers(adminContextPath + "/login").permitAll()
                        .anyRequest().authenticated()                       // 其他的需要登陆后才能访问
                )
                .formLogin(http -> http.loginProcessingUrl(adminContextPath + "/login").successHandler(successHandler).permitAll())
                .logout(http -> http.logoutUrl(adminContextPath + "/logout").permitAll())
                .cors(AbstractHttpConfigurer::disable)      // 开启跨域
                .csrf(AbstractHttpConfigurer::disable)      // 禁用跨站请求伪造防护
                .build();
    }

}
