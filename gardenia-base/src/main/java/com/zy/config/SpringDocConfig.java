package com.zy.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangYu
 * <p>
 * 目前Springfox-Swagger以及Knife4j提供的资源接口包括如下：
 * 资源	说明
 * /doc.html	                                Knife4j提供的文档访问地址
 * /v2/api-docs-ext	                            Knife4j提供的增强接口地址,自2.0.6版本后删除
 * /swagger-resources	                        Springfox-Swagger提供的分组接口
 * /v2/api-docs	                                Springfox-Swagger提供的分组实例详情接口
 * /swagger-ui.html            	                Springfox-Swagger提供的文档访问地址
 * /swagger-resources/configuration/ui	        Springfox-Swagger提供
 * /swagger-resources/configuration/security	Springfox-Swagger提供
 */
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SpringDocConfig implements WebMvcConfigurer {

    /** 是否开启 true 开启，false 关闭*/
    private String enabled;
    /** 名称 */
    private String title;
    /** 简介 */
    private String description;
    /** 作者 */
    private String author;
    /** 版本 */
    private String version;

    /** headers 公共的默认配置 */
//	private Map<String, String> headers = new HashMap<String, String>();

    /** 全局鉴权参数 */
    private List<String> security = new ArrayList<>();

    /** 定义分组 */
//    private List<SwaggerGroupConfigEntity> groupConfigs = new ArrayList<>();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * SpringDoc 标题、描述、版本等信息配置
     *
     * @return openApi 配置信息
     */
    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("网站-中心API文档")
                        .description("接口文档说明")
                        .version("v0.0.1")
                        .license(new License().name("专栏").url("http://zyGardenia.com")))
                .externalDocs(new ExternalDocumentation().description("项目地址").url("http://zyGardenia.com"))
                // 配置 Authorizations
//                .components(new Components().addSecuritySchemes("bearer-key",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")))
                ;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/sys/**")
//                .packagesToScan("com.zy")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("Gardenia")
                .pathsToMatch("/private/**")
                .build();
    }

    /**
     * Solve the of documentationPluginsBootstrapper  "null point exception"
     * @return
     */
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
}
