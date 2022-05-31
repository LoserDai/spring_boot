package com.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Loser
 * @date 2021年11月05日 14:57
 */
@Controller
public class HelloWorld {
    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, Object> helloWorld(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg","小嘴儿抹了蜜一样~");
        return map;
    }
}
