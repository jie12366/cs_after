package com.cs.demo.service.impl;

import com.cs.demo.entity.UserLike;
import com.cs.demo.mapper.UserLikeMapper;
import com.cs.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 19:46
 */
@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    UserLikeMapper userLikeMapper;

    @Autowired
    RedisServiceImpl redisService;

    @Override
    public int saveUserLike(UserLike userLike) {
        return userLikeMapper.saveUserLike(userLike.getUserId(),userLike.getNickName());
    }

    @Override
    public int deleteUserLike(int userId) {
        List<UserLike> userLikeList = listUserLikeById(userId);
        for (UserLike userLike : userLikeList){
            redisService.deleteKey1(String.valueOf(userLike.getUserId()),userLike.getNickName());
        }
        return userLikeMapper.deleteUserLike(userId);
    }

    @Override
    public UserLike getById(int userId, String nickName) {
        return userLikeMapper.getById(userId, nickName);
    }

    @Override
    public List<UserLike> listUserLikeById(int userId) {
        return userLikeMapper.listUserLikeById(userId);
    }

    @Override
    public void transLikedFromRedis2DB() {
        List<UserLike> userLikeList = redisService.getLikedFromRedis();
        for (UserLike userLike : userLikeList){
            UserLike userLike1= getById(userLike.getUserId(),userLike.getNickName());
            if (userLike1 == null){
                //数据库没有记录，存入数据库
                saveUserLike(userLike);
            }
        }
    }
}