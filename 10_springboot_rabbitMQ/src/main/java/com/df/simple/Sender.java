package com.df.simple;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月10日 19:28
 */
//发送者
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        //轻量级别的connection,封装了大部分API
        Channel channel = connection.createChannel();
        //队列
        String QUEUE_NAME = "simple_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息
        String msg = "您好呀~靓仔";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("Sender发送消息: " + msg);
        channel.close();
        connection.close();
    }
}
