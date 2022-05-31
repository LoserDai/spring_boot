package com.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Loser
 * @date 2021年11月09日 10:39
 */
@Controller
public class ErrorController {
    @RequestMapping("/exception1")
    public String exception1(){
        int i = 10 / 0;
        return "index";
    }
    @RequestMapping("/exception2")
    public String exception2(){
        String str = null;
        str.length();
        return "index";
    }
}
