package com.cs.demo.mapper;

import com.cs.demo.entity.Active;
import com.cs.demo.entity.ActiveCollect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 20:36
 */
@Mapper
public interface ActiveCollectMapper {

    /**
     * 将收藏活动的数据从redis中插入
     * @param userName
     * @param activeId
     * @return
     */
    @Insert("insert into active_collect(userName,activeId) values(#{userName},#{activeId})")
    int saveActiveCollect(String userName,int activeId);

    /**
     * 将活动id为该id的信息删除
     * @param activeId
     * @return
     */
    @Delete("delete from active_collect where activeId=#{activeId}")
    int deleteActiveCollect(int activeId);

    /**
     * 删除一条收藏信息
     * @param userName 用户名
     * @param activeId 活动id
     * @return
     */
    @Delete("delete from active_collect where userName=#{userName} and activeId=#{activeId}")
    int deleteOne(String userName,int activeId);

    /**
     * 判断该收藏信息是否存在
     * @param userName 用户名
     * @param activeId 活动id
     * @return
     */
    @Select("select * from active_collect where userName=#{userName} and activeId=#{activeId}")
    ActiveCollect getById(String userName,int activeId);

    /**
     * 根据活动id获取所有相关信息
     * @param activeId
     * @return
     */
    @Select("select * from active_collect where activeId=#{activeId}")
    List<ActiveCollect> listActiveCollect(int activeId);

    /**
     * 获取活动id的数量
     * @param activeId
     * @return
     */
    @Select("select count(*) from active_collect where activeId=#{activeId}")
    int countActiveCollect(int activeId);

    /**
     * 返回我收藏的所有活动信息
     * @param userName
     * @return
     */
    @Select("select a.* from active a join active_collect ac on a.activeId = ac.activeId " +
            "where ac.userName=#{userName}")
    List<Active> listActiveByUserNameByPage(String userName);

    /**
     * 判断收藏是否已经存在
     * @param userName 用户名
     * @param activeId 活动id
     * @return
     */
    @Select("select * from active_collect where userName=#{userName} and activeId=#{activeId}")
    ActiveCollect getByUserById(String userName,int activeId);
}