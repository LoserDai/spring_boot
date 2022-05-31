package com.df;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Loser
 * @date 2021年11月08日 12:01
 */
@SpringBootApplication
// @MapperScan 用户扫描MyBatis的Mapper接口
@MapperScan("com.df.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
