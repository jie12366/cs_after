package com.cs.demo.service;

import com.cs.demo.entity.UserLike;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 16:36
 */
@Service
public interface UserLikeService {

    /**
     * 将数据从redis中插入
     * @param userLike UserLike
     * @return
     */
    int saveUserLike(UserLike userLike);

    /**
     * 从数据库中删除该id的数据
     * @param userId
     * @return
     */
    int deleteUserLike(int userId);

    /**
     * 判断是否存在该记录
     * @param userId 用户id
     * @param nickName 点赞者昵称
     * @return
     */
    UserLike getById(int userId, String nickName);

    /**
     * 从数据库中获取该id的所有相关数据
     * @param userId
     * @return
     */
    List<UserLike> listUserLikeById(int userId);

    /**
     * 从redis中更新到数据库
     */
    void transLikedFromRedis2DB();
}