package com.cs.demo.config;

import com.cs.demo.service.impl.QuartzJobBeanImpl;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 15:21
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(QuartzJobBeanImpl.class)
                //任务标识
                .withIdentity(LIKE_TASK_IDENTITY)
                //即使没有Trigger关联时，也不需要删除该JobDetail
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
        //设置2小时执行一次
        .withIntervalInHours(2)
        .repeatForever();

        return TriggerBuilder.newTrigger()
                //单任务执行，可设置多任务
                .forJob(quartzDetail())
                //触发器标识
                .withIdentity(LIKE_TASK_IDENTITY)
                //调度执行
                .withSchedule(scheduleBuilder)
                .build();
    }
}