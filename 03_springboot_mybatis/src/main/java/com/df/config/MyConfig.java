package com.df.config;

import com.df.converter.DateConverter;
import com.df.converter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Loser
 * @date 2021年11月10日 11:10
 */
//配置日期转换器
/**
 * WebMvcConfigurer：是springmvc的配置类，可以个性化的定制框架，比如：日期转换器、拦截器
 */
@Component
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private DateConverter dateConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter);
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login");
    }
}
