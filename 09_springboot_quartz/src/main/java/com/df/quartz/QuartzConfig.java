package com.df.quartz;


import com.df.scheduled.JobDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Loser
 * @date 2021年11月09日 16:25
 */
@Configuration //<beans>
public class QuartzConfig {
    //1、做什么事
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(JobDemo jobDemo){
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(jobDemo);
        jobDetail.setTargetMethod("execute");
        return jobDetail;
    }

    //2、什么是做
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setCronExpression("*/1 * * * * ?");
        //setJobDetail(JobDetail jobDetail)
        //public JobDetail getObject()
        trigger.setJobDetail(jobDetail.getObject());
        return trigger;
    }
    //3、什么时候做什么事
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean trigger){
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(trigger.getObject());
        return scheduler;
    }
}
