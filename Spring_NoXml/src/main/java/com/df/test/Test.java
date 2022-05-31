package com.df.test;

import com.df.config.SpringConfiguration;
import com.df.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * @author Loser
 * @date 2021年11月05日 10:03
 */
public class Test {
    public static void main(String[] args) {
        // ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //将配置文件删除, 通过注解方式改造
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = ac.getBean("userServiceImpl", UserService.class);
        userService.addUser();
    }
}
