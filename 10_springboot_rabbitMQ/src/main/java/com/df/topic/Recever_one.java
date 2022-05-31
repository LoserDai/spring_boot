package com.df.topic;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 14:52
 */
public class Recever_one {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String EXCHANGE_NAME = "topic_exchange";
        String QUEUE_NAME = "topic_queue";
        //队列持久化
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //绑定到交换机上
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.insert");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[接受者]:" + msg);
            }
        };
        //监听
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
