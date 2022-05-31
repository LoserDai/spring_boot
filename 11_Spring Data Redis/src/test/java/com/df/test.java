package com.df;

import com.df.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author Loser
 * @date 2021年11月23日 17:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class test {

    @Autowired
    private RedisTemplate redisTemplate;

    //1、StringRedisSerializer序列化器
    @Test
    public void testSet(){
        redisTemplate.opsForValue().set("nam","张三丰");
    }
    @Test
    public void testGet(){
        String nam = (String) redisTemplate.opsForValue().get("nam");
        System.out.println(nam);
    }

    //2、ObjectMapper手动序列化
    @Test
    public void testSetUserJson() throws JsonProcessingException {
        User user = new User(1,"戴疯",18);
        ObjectMapper om = new ObjectMapper();
        redisTemplate.opsForValue().set("user",om.writeValueAsString(user));
    }
    @Test
    public void testGetUserJson() throws IOException {
        String json = (String) redisTemplate.opsForValue().get("user");
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(json, User.class);
        System.out.println(user);
    }

    //3、Jackson2JsonRedisSerializer序列化器
    @Test
    public void testSetUserJson2() throws JsonProcessingException {
        //Jackson2JsonRedisSerializer作用：user对象转成json串
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(User.class));
        User user = new User(2,"戴疯2",182);
        redisTemplate.opsForValue().set("user2",user);
    }
    @Test
    public void testGetUserJson2() throws IOException {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(User.class));
        User user = (User) redisTemplate.opsForValue().get("user2");
        System.out.println(user);
    }

    //4、通用序列化器
    @Test
    public void testSetUserJson3() throws JsonProcessingException {
        User user = new User(3,"戴疯3",183);
        redisTemplate.opsForValue().set("user_three",user);
    }
    @Test
    public void testGetUserJson3() throws IOException {
        User user = (User) redisTemplate.opsForValue().get("user_three");
        System.out.println(user);
    }
    //每次存取pojo数据都要重新设置value的序列化器 ==> 设置通用序列化器
}
