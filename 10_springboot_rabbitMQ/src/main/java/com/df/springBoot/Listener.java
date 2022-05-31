package com.df.springBoot;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Loser
 * @date 2021年11月11日 15:43
 */
//@Component
//注入到容器中
public class Listener {
    /**
     * 监听器三大要素
     * queue:
     * exchange
     * routing key
     */
    @RabbitListener(bindings = {@QueueBinding(
           value = @Queue(value = "springboot_queue",durable = "true"),
           exchange = @Exchange(value = "springboot_exchange",type = ExchangeTypes.TOPIC),
            key = "*.*")
    })
    public void getMsg(String msg, Channel channel, Message message) throws IOException {
        System.out.println("[接受者:] " + msg);
        //手动接收
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}
