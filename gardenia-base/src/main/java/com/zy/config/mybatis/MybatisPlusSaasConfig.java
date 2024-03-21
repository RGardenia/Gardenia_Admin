package com.zy.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zy.common.annotation.MapperScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单数据源配置（gardenia.datasource.open = false 时生效）
 *
 * @Author zy
 */
@Configuration
@MapperScanner(value = {"${mybatis.mapperScanner.basePackage}"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisPlusSaasConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor

        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

}
