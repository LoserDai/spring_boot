package com.df.simple;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月10日 19:49
 */
public class Recver {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        //轻量级别的connection,封装了大部分API
        Channel channel = connection.createChannel();
        //1. 队列,如果队列已存在则不再声明
        String QUEUE_NAME = "simple_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //2. 定义消息的接收者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try{
                    String msg = new String(body);
                    System.out.println("Resver 接受到了: " + msg);
                    //int i = 7/0;
                    /**模拟发短信,扣库存挂掉
                     * 手动确认
                     * deliveryTag: 该消息的index
                     * multiple: 是否批量确认
                     */
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        //确认
        /**
         * QUEUE_NAME:队列的名称
         * false:是否自动确认,最好是false
         * consumer:指定消费者
         */
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
