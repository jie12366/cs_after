package com.cs.demo.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 16:01
 */
public class QuartzJobBeanImpl extends QuartzJobBean {
    @Autowired
    UserLikeServiceImpl userLikeService;

    @Autowired
    ActiveCollectServiceImpl activeCollectService;

    @Autowired
    RedisServiceImpl redisService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        userLikeService.transLikedFromRedis2DB();
        activeCollectService.transCollectFromRedis();
    }
}