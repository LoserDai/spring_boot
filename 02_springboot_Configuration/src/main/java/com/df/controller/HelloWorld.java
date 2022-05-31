package com.df.controller;

import com.df.pojo.Dog;
import com.df.pojo.Pig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Loser
 * @date 2021年11月05日 14:57
 */
@Controller
public class HelloWorld {
    @Autowired
    private Dog dog;
    @Autowired
    private Pig pig;

    @RequestMapping("/hello")
    @ResponseBody
    public Dog helloWorld(){
        System.out.println(dog.getName());
        return dog;
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public Pig hello2(){
        System.out.println(pig.getName());
        return pig;
    }
}
