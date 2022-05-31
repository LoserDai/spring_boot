package com.df.durable;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 13:55
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String EXCHANGE_NAME = "durable_exchange";
        //设置交换机为可持久化的
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT,true);
        String msg = "Hello,靓女~";
        //设置消息持久化
        channel.basicPublish(EXCHANGE_NAME,"", MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());
        System.out.println("信息发送: " + msg);
        channel.close();
        connection.close();

    }
}
