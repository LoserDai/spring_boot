package com.df.work;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 11:00
 */
public class Send {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //队列
        String QUEUE_NAME = "work_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息
        for (int i = 0; i < 50; i++) {
            String msg = "您好呀~靓仔: " + i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("send发送了: " + msg );
            Thread.sleep(i * 2);
        }
        channel.close();
        connection.close();
    }
}
