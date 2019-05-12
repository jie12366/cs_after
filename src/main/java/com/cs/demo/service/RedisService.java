package com.cs.demo.service;

import com.cs.demo.entity.ActiveCollect;
import com.cs.demo.entity.UserLike;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/22 15:38
 */
@Service
public interface RedisService {
    /**
     * 键值对存入redis
     * @param key 键
     * @param value 值
     * @return Boolean
     */
    boolean set(final String key, final String value);

    /**
     * 通过键从redis获取值
     * @param key 键
     * @return 字符串
     */
    String get(final String key);

    /**
     *值自增
     * @param key 键
     * @return String
     */
    String incr(final String key);

    /**
     * 键自减
     * @param key
     */
    String decr(final String key);

    /**
     * 两个参数，合并为一个key存在redis
     * @param userId 第一个键
     * @param nickName 第二个键
     */
    void saveKey1(String userId, String nickName);

    /**
     * 删除key
     * @param userId
     * @param nickName
     */
    void deleteKey1(String userId, String nickName);

    /**
     * 两个参数，合并为一个key存在redis
     * @param userName 第一个键
     * @param activeId 第二个键
     */
    void saveKey2(String userName, String activeId);

    /**
     * 删除key
     * @param userName
     * @param activeId
     */
    void deleteKey2(String userName, String activeId);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<UserLike> getLikedFromRedis();

    /**
     * 获取Redis中存储的所有收藏数据
     * @return
     */
    List<ActiveCollect> getCollectFromRedis();
}