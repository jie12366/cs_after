package com.cs.demo.mapper;

import com.cs.demo.entity.UserLike;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 14:29
 */
@Mapper
public interface UserLikeMapper {

    /**
     * 将数据从redis中插入
     * @param userId
     * @param nickName
     * @return
     */
    @Insert("insert into user_like values(#{userId},#{nickName})")
    int saveUserLike(int userId,String nickName);

    /**
     * 从数据库中删除该id的数据
     * @param userId
     * @return
     */
    @Delete("delete from user_like where userId=#{userId}")
    int deleteUserLike(int userId);

    /**
     * 判断是否存在该记录
     * @param userId 用户id
     * @param nickName 点赞者昵称
     * @return
     */
    @Select("select * from user_like where userId=#{userId} and nickName=#{nickName}")
    UserLike getById(int userId,String nickName);

    /**
     * 从数据库中获取该id的所有相关数据
     * @param userId
     * @return
     */
    @Select("select * from user_like where userId=#{userId}")
    List<UserLike> listUserLikeById(int userId);
}