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
 * @date 2021年11月11日 19:55
 */
//@Component
public class Topic_One {
    @RabbitListener(bindings = @QueueBinding(value = @Queue("topic_queue_1"),
            exchange =@Exchange(value = "topic_exchange",type = ExchangeTypes.TOPIC),
            key = "shop.*"
    ))
    public void fanOut_One(String msg, Channel channel, Message message) throws IOException {
        System.out.println("[接受者α:] " + msg);
        //手动接收
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
