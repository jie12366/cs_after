package com.cs.demo.utils;

import java.util.Map;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 18:24
 */
public class RedisKeyUtils {

    /**
     * 保存用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    /**
     * 保存用户收藏数据的key
     */
    public static final String MAP_KEY_USER_COLLECT = "MAP_USER_COLLECT";

    /**
     * 拼接活动id和浏览类型作为key ，形式如 1::scan
     * @param activeId
     * @param type
     * @return
     */
    public static String getKey(String activeId,String type){
        StringBuilder sb = new StringBuilder();
        sb.append(activeId);
        sb.append("::");
        sb.append(type);
        return sb.toString();
    }

    public static String[] getStrings(Map.Entry<Object,Object> entry){
        String key = (String)entry.getKey();
        //分离出活动id和浏览类型
        String[] keys = key.split("::");
        return keys;
    }
}