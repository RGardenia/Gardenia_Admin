package com.zy.service.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangYu
 *
 * 目前Springfox-Swagger以及Knife4j提供的资源接口包括如下：
 * 资源	说明
 *      /doc.html	                                Knife4j提供的文档访问地址
 *      /v2/api-docs-ext	                        Knife4j提供的增强接口地址,自2.0.6版本后删除
 *      /swagger-resources	                        Springfox-Swagger提供的分组接口
 *      /v2/api-docs	                            Springfox-Swagger提供的分组实例详情接口
 *      /swagger-ui.html            	            Springfox-Swagger提供的文档访问地址
 *      /swagger-resources/configuration/ui	        Springfox-Swagger提供
 *      /swagger-resources/configuration/security	Springfox-Swagger提供
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/public/**")
                .build();
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("Gardenia")
                .pathsToMatch("/private/**")
                .build();
    }

//    @Bean
//    public Docket webApiConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.zy.service"))
////                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
////                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .paths(PathSelectors.any())
//                .build();
//    }

//    private ApiInfo webApiInfo(){
//        return new ApiInfoBuilder()
//                .title("网站-中心API文档")
//                .description("本文档描述了课程中心微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("zyGardenia", "http://zyGardenia.com", "1511502172@qq.com"))
//                .build();
//    }

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
