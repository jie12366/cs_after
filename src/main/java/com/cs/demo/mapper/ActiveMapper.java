package com.cs.demo.mapper;

import com.cs.demo.entity.Active;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 9:10
 */
@Mapper
public interface ActiveMapper {

    /**
     * 插入数据
     * @param title
     * @param content
     * @param money
     * @param category
     * @param activeTime
     * @param address
     * @param annex
     * @param name
     * @param phone
     * @param social
     * @param socialNumber
     * @param isPublic
     * @param createTime
     * @return
     */
    @Insert("insert into active(title,content,money,category,activeTime,address,annex," +
            "name,phone,social,socialNumber,isPublic,createTime) " +
            "values(#{title},#{content},#{money},#{category},#{activeTime},#{address}," +
            "#{annex},#{name},#{phone},#{social},#{socialNumber},#{isPublic},#{createTime})")
    int saveActive(String title,String content,String money,String category,String activeTime,
                   String address,String annex,String name,String phone,String social,String socialNumber,int isPublic,String createTime);

    /**
     * 获取刚刚自增的id
     * @return
     */
    @Select("select max(activeId) from active")
    int getMaxId();

    /**
     * 根据id更新活动信息
     * @param title
     * @param content
     * @param money
     * @param category
     * @param activeTime
     * @param address
     * @param annex
     * @param name
     * @param phone
     * @param social
     * @param socialNumber
     * @param isPublic
     * @param updateTime
     * @param activeId
     * @return
     */
    @Update("update active set title=#{title},content=#{content},money=#{money}" +
            "category=#{category},activeTime=#{activeTime},address=#{address},annex=#{annex},name=#{name}" +
            "phone=#{phone},social=#{social},socialNumber=#{socialNumber},isPublic=#{isPublic},updateTime=#{updateTime} " +
            "where activeId=#{activeId}")
    int updateActive(String title,String content,String money,String category,String activeTime,
                     String address,String annex,String name,String phone,String social,String socialNumber,int isPublic,
                     String updateTime,int activeId);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @Select("select * from active where id=#{id}")
    Active getActiveById(int id);

    /**
     * 分页获取信息
     * @return
     */
    @Select("select * from active")
    List<Active> listActiveByPage();

    /**
     * 根据id删除活动信息
     * @param id
     * @return
     */
    @Delete("delete from active where activeId=#{activeId}")
    int deleteActive(int id);
}