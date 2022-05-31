package com.df.topic;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 14:48
 */
public class Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String EXCHANGE_NAME = "topic_exchange";
        String msg = "请查收消息PLUS";
        //交换机持久化
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC,true);
        //消息持久化
        channel.basicPublish(EXCHANGE_NAME,"shop.*", MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());
        System.out.println("[发送者:] " + msg);
        channel.close();
        connection.close();
    }
}
