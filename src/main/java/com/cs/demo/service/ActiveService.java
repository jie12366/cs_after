package com.cs.demo.service;

import com.cs.demo.entity.Active;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/5 16:21
 */
@Service
public interface ActiveService {

    /**
     * 保存活动信息
     * @param active
     * @return
     */
    int saveActive(Active active);

    /**
     * 获取刚刚自增的id
     * @return
     */
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
     * @param id
     * @return
     */
    int updateActive(String title, String content, String money, String category, String activeTime,
                     String address, String annex, String name, String phone, String social, String socialNumber, int isPublic, int id);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Active getActiveById(int id);

    /**
     * 分页获取信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Active> listActiveByPage();

    /**
     * 根据id删除活动信息
     * @param id
     * @return
     */
    int deleteActive(int id);
}