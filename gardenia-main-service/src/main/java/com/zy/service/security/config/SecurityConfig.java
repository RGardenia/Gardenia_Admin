package com.zy.service.security.config;

import com.zy.service.security.filter.JWTAuthenticationFilter;
import com.zy.service.security.handler.UserAccessDeniedHandler;
import com.zy.service.security.handler.UserLoginFailureHandler;
import com.zy.service.security.handler.UserLogoutSuccessHandler;
import com.zy.service.security.handler.UserNotLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security配置类
 *
 * @author zy
 * @since 2019-11-18
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置哪些请求不拦截
     *
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(AUTH_WHITELIST);
    }

    /**
     * -- springDoc ui忽略
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**", "/swagger-ui/**",
            "/swagger-ui.html", "/v3/**", "/v2/**",
            "/webjars/**", "/doc.html", "/profile/**"
    };

    /**
     * 安全权限配置
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // configure HTTP security...
//                .successHandler(new UserLoginSuccessHandler()) // 配置登录成功处理类
//                .failureHandler(new UserLoginFailureHandler())   // 配置登录失败处理类

        return httpSecurity.authorizeHttpRequests((requests) -> requests
                        .requestMatchers(JWTConfig.antMatchers.split(",")).permitAll()      // 获取白名单（不进行权限验证）
                        .requestMatchers(AUTH_WHITELIST).permitAll()        // 后端接口规范 放行
                        .anyRequest().authenticated()                       // 其他的需要登陆后才能访问
                ).formLogin(http -> http.loginProcessingUrl("/login").defaultSuccessUrl("/index").permitAll())
                .httpBasic(http -> http.authenticationEntryPoint(new UserNotLoginHandler()))           // 配置未登录处理类
                .exceptionHandling(http -> http.accessDeniedHandler(new UserAccessDeniedHandler()))     // 配置没有权限处理类

                .logout(http -> http.logoutUrl("/logout").logoutSuccessHandler(new UserLogoutSuccessHandler())) // 配置登出地址 & 登出处理器
//                .addFilterBefore(new JWTAuthenticationFilter(this.authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilter(new JWTAuthenticationFilter(this.authenticationManager())) // 添加JWT过滤器
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // 禁用session（使用Token认证）
                .rememberMe(http -> http.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(userDetailsService)) // 存储 Token 一般使用 redis ，这里使用 关系型数据库 mysql 存储
                .cors(AbstractHttpConfigurer::disable)      // 开启跨域
                .csrf(AbstractHttpConfigurer::disable)      // 禁用跨站请求伪造防护
//                .headers(http -> http.cacheControl())       // 禁用缓存
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        ProviderManager pm = new ProviderManager(daoAuthenticationProvider);
//        return pm;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean
    protected PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        /* jdbcTokenRepository.setCreateTableOnStartup(true); */
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
