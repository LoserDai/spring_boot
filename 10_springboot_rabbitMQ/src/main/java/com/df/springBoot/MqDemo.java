package com.df.springBoot;

import com.df.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Loser
 * @date 2021年11月11日 15:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MqDemo {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * TOPIC类型的消息队列
     * @throws InterruptedException
     */
    @Test
    public void testSend() throws InterruptedException {
        String msg = "打小看你行~";
        String routingKey = "a.b";
        String EXCHANGE_NAME = "springboot_exchange";
        amqpTemplate.convertAndSend(EXCHANGE_NAME,routingKey,msg);
        //等待十秒再关闭资源
        Thread.sleep(10000);
    }

    /**
     * 最简单的消息队列
     * @throws InterruptedException
     */
    @Test
    public void simpleSend() throws InterruptedException {
        String msg = "这是简单的send";
        String QUEUE_NAME = "springboot_queue";
        amqpTemplate.convertAndSend(QUEUE_NAME,msg);
        //等待十秒再关闭资源
        Thread.sleep(10000);
    }

    /**
     * work类型的消息队列
     * @throws InterruptedException
     */
    @Test
    public void workListener() throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            String msg = "work方式的消息" + i;
            String QUEUE_NAME = "work_queue";
            amqpTemplate.convertAndSend(QUEUE_NAME,msg);
        }
        //等待十秒再关闭资源
        Thread.sleep(10000);
    }

    /**
     * fanout类型的队列
     * @throws InterruptedException
     */
    @Test
    public void fanOut() throws InterruptedException {
        String msg = "这是fanout方式的消息~";
        String EXCHANGE_NAME = "fanout_exchange";
        amqpTemplate.convertAndSend(EXCHANGE_NAME,"",msg);
        //等待十秒再关闭资源
        Thread.sleep(10000);
    }

    /**
     * direct类型的消息队列
     * @throws InterruptedException
     */
    @Test
    public void direct() throws InterruptedException {
        String msg = "这是direct方式的消息~";
        String EXCHANGE_NAME = "direct_exchange";
        amqpTemplate.convertAndSend(EXCHANGE_NAME,"error",msg);
        //等待十秒再关闭资源
        Thread.sleep(10000);
    }

    /**
     * topic类型的消息队列
     * @throws InterruptedException
     */
    @Test
    public void topic() throws InterruptedException {
        String msg = "这是topic方式的消息~";
        String EXCHANGE_NAME = "topic_exchange";
        amqpTemplate.convertAndSend(EXCHANGE_NAME,"shop.update",msg);
        //等待十秒再关闭资源
        Thread.sleep(20000);
    }

}
