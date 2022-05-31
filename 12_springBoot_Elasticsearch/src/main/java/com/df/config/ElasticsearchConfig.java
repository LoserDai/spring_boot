package com.df.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Loser
 * @date 2021年11月25日 15:31
 *
 */
@Configuration
public class ElasticsearchConfig extends ElasticsearchProperties {
    @Bean
    public RestHighLevelClient getHighLevelClient(){
        String[] hosts = getClusterNodes().split(",");
        //创建hosts.length长度的一个httpHost数组
        HttpHost[] httpHost = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            //分割ip与端口
            httpHost[i] = new HttpHost(hosts[i].split(":")[0],
                    Integer.valueOf(hosts[i].split(":")[1]));
        }
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
