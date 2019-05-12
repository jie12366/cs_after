package com.cs.demo.service.impl;

import com.cs.demo.entity.Active;
import com.cs.demo.mapper.ActiveMapper;
import com.cs.demo.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/5 16:23
 */
@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;

    @Override
    public int saveActive(Active active) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return activeMapper.saveActive(active.getTitle(), active.getContent(), active.getMoney(), active.getCategory(),
                active.getActiveTime(), active.getAddress(), active.getAnnex(), active.getName(), active.getPhone(),
                active.getSocial(),active.getSocialNumber(), active.getIsPublic(), sdf.format(date));
    }

    @Override
    public int getMaxId() {
        return activeMapper.getMaxId();
    }

    @Override
    public int updateActive(String title, String content, String money, String category,
                            String activeTime, String address, String annex, String name, String phone,
                            String social,String socialNumber, int isPublic, int activeId) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return activeMapper.updateActive(title, content, money, category, activeTime, address,
                             annex, name, phone, social,socialNumber, isPublic, sdf.format(date), activeId);
    }

    @Override
    public Active getActiveById(int id) {
        return activeMapper.getActiveById(id);
    }

    @Override
    public List<Active> listActiveByPage(int currentPage, int pageSize) {
        Map<String ,Object> map = new HashMap<>(5);
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
        return activeMapper.listActiveByPage(map);
    }

    @Override
    public int deleteActive(int id) {
        return activeMapper.deleteActive(id);
    }
}