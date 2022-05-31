package com.df.springBoot;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Loser
 * @date 2021年11月11日 17:03
 */
//@Component
public class SimpleListener {
    @RabbitListener(queuesToDeclare = {@Queue("work_queue")})
    public void simpleListener(String msg, Channel channel, Message message){
        System.out.println("[接收者B:] " + msg);
        //手动确认
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
