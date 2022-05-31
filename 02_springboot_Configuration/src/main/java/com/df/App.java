package com.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Loser
 * @date 2021年11月05日 14:59
 */
@SpringBootApplication //开启自动配置
public class App  {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
