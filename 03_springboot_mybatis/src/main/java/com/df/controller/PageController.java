package com.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Loser
 * @date 2021年11月10日 11:12
 */
//配置欢迎页面
@Controller
public class PageController {

    @RequestMapping("/")
    public String toPage(){
            return "login";
        }
}
