package com.df.springBoot;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Loser
 * @date 2021年11月11日 17:39
 */
//@Component
public class WorkListener {
    @RabbitListener(queuesToDeclare = {@Queue("work_queue")})
    public void workListener(String msg, Channel channel, Message message) throws InterruptedException {
        System.out.println("[接收者A:] " + msg);
        Thread.sleep(10000);
        //手动确认
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
