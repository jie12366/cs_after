package com.cs.demo.service.impl;

import com.cs.demo.entity.ActiveCollect;
import com.cs.demo.entity.UserLike;
import com.cs.demo.service.RedisService;
import com.cs.demo.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/22 15:42
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public boolean set(final String key, final String value) throws DataAccessException{
        return redisTemplate.execute((RedisConnection connection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
    }

    @Override
    public String incr(final String key) throws DataAccessException{
        return redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Long value = redisConnection.incr(serializer.serialize(key));
            return String.valueOf(value);
        });
    }

    @Override
    public String decr(final String key)throws DataAccessException {
        return redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Long value = redisConnection.decr(serializer.serialize(key));
            return String.valueOf(value);
        });
    }

    @Override
    public String get(final String key) throws DataAccessException{
        return redisTemplate.execute((RedisConnection connection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value =  connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
    }

    @Override
    public void saveKey1(String userId, String nickName) {
        String key = RedisKeyUtils.getKey(userId,nickName);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key,userId);
    }

    @Override
    public void deleteKey1(String userId, String nickName) {
        String key = RedisKeyUtils.getKey(userId,nickName);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
    }

    @Override
    public void saveKey2(String userName, String activeId) {
        String key = RedisKeyUtils.getKey(userName,activeId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_COLLECT,key,userName);
    }

    @Override
    public void deleteKey2(String userName, String activeId) {
        String key = RedisKeyUtils.getKey(userName,activeId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_COLLECT,key);
    }

    @Override
    public List<UserLike> getLikedFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            String[] keys = RedisKeyUtils.getStrings(cursor.next());
            String userId = keys[0];
            String nickName = keys[1];
            UserLike userLike = new UserLike(Integer.parseInt(userId),nickName);
            list.add(userLike);
        }
        return list;
    }

    @Override
    public List<ActiveCollect> getCollectFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_COLLECT, ScanOptions.NONE);
        List<ActiveCollect> list = new ArrayList<>();
        while (cursor.hasNext()){
            String[] keys = RedisKeyUtils.getStrings(cursor.next());
            String userName = keys[0];
            String activeId = keys[1];
            ActiveCollect activeCollect = new ActiveCollect();
            activeCollect.setUserName(userName);
            activeCollect.setActiveId(Integer.parseInt(activeId));
            list.add(activeCollect);
        }
        return list;
    }
}