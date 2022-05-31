package com.df.scheduled;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Loser
 * @date 2021年11月09日 16:25
 */
@Component
public class JobDemo {
    public void execute(){
        System.out.println("现在是北京时间: " + new Date());
    }
}
