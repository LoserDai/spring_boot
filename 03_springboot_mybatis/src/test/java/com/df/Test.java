package com.df;

import com.df.pojo.User;
import com.df.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author Loser
 * @date 2021年11月08日 12:08
 */
/**
 *  main方法：
 *		ApplicationContext ac=new
 *       			ClassPathXmlApplicationContext("classpath:applicationContext.xml");
 *  junit与spring整合：
 *      @RunWith(SpringJUnit4ClassRunner.class)：让junit与spring环境进行整合
 *   	@Contextconfiguartion("classpath:applicationContext.xml")
 *  junit与SpringBoot整合：
 *		@RunWith(SpringJUnit4ClassRunner.class)：让junit与spring环境进行整合
 *		@SpringBootTest(classes={App.class})：加载SpringBoot启动类。启动springBoot
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class Test {
    @Autowired
    private UserService userService;

    @org.junit.Test
    public void testAddUser(){
        User user = new User();
        user.setId(1);
        user.setNam("陈冠希");
        user.setPwd("111");
        user.setSex(1);
        user.setBirth(new Date());
        this.userService.addUser(user);
    }
}
