package com.zy.common.annotation;

import com.zy.config.mybatis.MapperScannerRegistrar;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Gardenia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MapperScannerRegistrar.class})
public @interface MapperScanner {
    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;

    String sqlSessionTemplateRef() default "";

    String sqlSessionFactoryRef() default "";

    Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;

    String lazyInitialization() default "";

    String defaultScope() default "";
}
