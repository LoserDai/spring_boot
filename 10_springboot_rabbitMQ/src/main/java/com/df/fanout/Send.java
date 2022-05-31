package com.df.fanout;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 11:22
 */
public class Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        //轻量级别的connection,封装了大部分API
        Channel channel = connection.createChannel();
        //声明交换机,并设定其类型为fanout
        String EXCHANGE_NAME = "fanout_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT);
        String msg = "Hello EveryOne";
        //发送到exchange
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("信息发送者: " + msg);
        channel.close();
        connection.close();
    }
}
