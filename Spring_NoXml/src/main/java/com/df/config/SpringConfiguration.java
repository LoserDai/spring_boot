package com.df.config;

import com.df.pojo.Cat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @author Loser
 * @date 2021年11月05日 10:07
 */
@Configuration //相当于spring配置中的<beans>
@ComponentScan("com.df") //相当于 <context:component-scan base-package="com.df"></context:component-scan>
 @PropertySource("classpath:config.properties")
//@Import({SpringConfiguration_Other.class})//导入另一个配置类的配置
public class SpringConfiguration {

    @Value("${nam}")
    private String nam;
    @Value("${age}")
    private Integer age;

    @Bean
    public Cat findCat(){
        Cat cat = new Cat();
        cat.setCatName(nam);
        cat.setAge(age);
        return cat;
    }
}
