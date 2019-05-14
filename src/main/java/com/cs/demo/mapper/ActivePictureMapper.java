package com.cs.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/10 12:27
 */
@Mapper
public interface ActivePictureMapper {

    /**
     * 存入活动id对应的图片
     * @param activeId
     * @param picture
     * @return
     */
    @Insert("insert into active_picture values(#{activeId},#{picture})")
    int savePicture(int activeId,String picture);

    /**
     * 根据活动id取出所有图片
     * @param activeId
     * @return
     */
    @Select("select picture from active_picture where activeId=#{activeId}")
    List<String> getPictureById(int activeId);

    /**
     * 删除活动id对应的图片
     * @param activeId
     * @return
     */
    @Delete("delete from active_picture where activeId=#{activeId}")
    int deletePictureById(int activeId);
}