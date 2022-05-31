package com.df.work;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 11:05
 */
public class Recever_Two {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME = "work_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("接收到了: " + msg);
                //手动接收
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
