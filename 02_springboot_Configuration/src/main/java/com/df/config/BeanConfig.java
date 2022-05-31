package com.df.config;

import com.df.pojo.Dog;
import com.df.pojo.Pig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Loser
 * @date 2021年11月08日 11:18
 */
@Configuration //<beans>
public class BeanConfig {
    @Bean
    @ConfigurationProperties("pig")//读dog开头的配置，并通过set方法注入到“dog”
    public Pig pig(){
        return new Pig();
    }

}
