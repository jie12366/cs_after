package com.cs.demo;

import com.cs.demo.cache.RedisCache;
import com.cs.demo.entity.Active;
import com.cs.demo.entity.UserLike;
import com.cs.demo.mapper.ActivePictureMapper;
import com.cs.demo.service.ActiveService;
import com.cs.demo.service.UserLikeService;
import com.cs.demo.service.impl.UploadServiceImpl;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ActiveService a;

    private RedisCache redisCache;

    @Test
    public void contextLoads(){

    }

}
