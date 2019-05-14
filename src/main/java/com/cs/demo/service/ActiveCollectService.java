package com.cs.demo.service;

import com.cs.demo.entity.Active;
import com.cs.demo.entity.ActiveCollect;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 20:55
 */
@Service
public interface ActiveCollectService {

    /**
     * 将收藏活动的数据从redis中插入
     * @param ActiveCollect
     * @return
     */
    int saveActiveCollect(ActiveCollect ActiveCollect);

    /**
     * 将活动id为该id的信息删除
     * @param activeId
     * @return
     */
    int deleteActiveCollect(int activeId);

    /**
     * 判断该收藏信息是否存在
     * @param userName 用户名
     * @param activeId 活动id
     * @return
     */
    ActiveCollect getById(String userName, int activeId);

    /**
     * 根据活动id获取所有相关信息
     * @param activeId
     * @return
     */
    List<ActiveCollect> listActiveCollect(int activeId);

    /**
     * 返回我收藏的所有活动信息
     * @param userName
     * @return
     */
    List<Active> listActiveByUserNameByPage(String userName);

    /**
     * 将redis中的数据更新到数据库
     */
    void transCollectFromRedis();
}