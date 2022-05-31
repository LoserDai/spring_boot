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
 * @date 2021年11月11日 19:14
 */
@Component
public class FanOut_two {
    @RabbitListener(bindings = @QueueBinding(value = @Queue("springboot_queue_2"),
            exchange =@Exchange(value = "fanout_exchange",type = ExchangeTypes.FANOUT)
    ))
    public void fanOut_two(String msg, Channel channel, Message message) throws IOException {
        System.out.println("[接受者B:] " + msg);
        //手动接收
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
