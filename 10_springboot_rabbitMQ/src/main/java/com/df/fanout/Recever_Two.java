package com.df.fanout;

import com.df.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月11日 11:27
 */
public class Recever_Two {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        String QUEUE_NAME = "fanout_queue2";
        String EXCHANGE_NAME = "fanout_exchange";
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[消费者2]: 接收到了" +  msg);
            }
        };
        // 监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
