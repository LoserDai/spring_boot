package com.df.controller;

import com.df.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Loser
 * @date 2021年11月08日 15:00
 */
@Controller
public class Hello {
    @RequestMapping("/show1")
    public String show1(Model model){
        model.addAttribute("msg","WDNMD");
        return "hello";
    }
    @RequestMapping("/strings")
    public String strings(Model model){
        model.addAttribute("msg","你是不是玩不起~");
        return "strings";
    }

    @RequestMapping("/dates")
    public String dates(Model model){
        model.addAttribute("birth",new Date());
        return "dates";
    }

    @RequestMapping("/each")
    public String EACH(Model model){
        ArrayList<User> list = new ArrayList<>();
        User user0 = new User(1,"李逍遥",21);
        User user1 = new User(2,"赵灵儿",19);
        User user2 = new User(3,"拜月",44);

        list.add(user0);
        list.add(user1);
        list.add(user2);
        model.addAttribute("list", list);
        return "each";
    }

    /**
     * restful传参,访问toRestful页面
     * @return
     */
    @RequestMapping("/toRestful")
    public String toRestful(){
        return "restful";
    }

    /**
     * restful传参
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/user/{id}/{name}")
    @ResponseBody
    public String restful(@PathVariable Integer id,@PathVariable String name){
        System.out.println("接收到的id以及name: "+ id + name);
        return id + "=====" + name;
    }
}
