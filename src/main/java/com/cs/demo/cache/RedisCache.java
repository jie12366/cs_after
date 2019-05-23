package com.cs.demo.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/23 10:00
 */
public class RedisCache implements Cache{
    private final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    /**
     * 缓存实例的id
     */
    private String id;

    /**
     * 读写锁，保证缓存读写的原子性
     */
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private RedisTemplate redisTemplate;

    /**
     * 设置redis过期时间为30分钟
     */
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    public RedisCache(String id){
        if (id == null){
            throw new IllegalArgumentException("Cache instance require an ID");
        }
        this.id = id;
    }

    private RedisTemplate getRedisTemplate(){
        if (redisTemplate == null){
            //获取RedisTemplate的bean
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key,value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("put query result to redis");
        }catch (Throwable t){
            logger.error("put query result failed");
        }
    }

    @Override
    public Object getObject(Object key) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations operations = redisTemplate.opsForValue();
            logger.debug("get query result to redis");
            return operations.get(key);
        }catch (Throwable t){
            logger.error("get query result failed");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(key);
            logger.debug("remove cache query from redis");
        }catch (Throwable t){
            logger.error("remove cache query failed");
        }
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback)connection ->{
            connection.flushDb();
            return null;
        });
        logger.debug("clear all cache from the redis");
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReentrantReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}