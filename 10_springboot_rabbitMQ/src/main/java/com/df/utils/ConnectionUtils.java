package com.df.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Loser
 * @date 2021年11月10日 19:19
 */
public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接ip
        factory.setHost("192.168.32.132");
        //连接的端口
        factory.setPort(5672);
        //账号信息,密码
        factory.setUsername("admin");
        factory.setPassword("1111");
        //virtualHost
        factory.setVirtualHost("/");
        //返回
        return factory.newConnection();

    }
}
