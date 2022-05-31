package com.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowController {
    @RequestMapping("/show")
    public String show(){
        System.out.println("sh232312..");
        return "index";
    }
}
