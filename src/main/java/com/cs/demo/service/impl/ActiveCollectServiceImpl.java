package com.cs.demo.service.impl;

import com.cs.demo.entity.Active;
import com.cs.demo.entity.ActiveCollect;
import com.cs.demo.mapper.ActiveCollectMapper;
import com.cs.demo.service.ActiveCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/9 20:56
 */
@Service
public class ActiveCollectServiceImpl implements ActiveCollectService {

    @Autowired
    ActiveCollectMapper activeCollectMapper;

    @Autowired
    RedisServiceImpl redisService;

    @Override
    public int saveActiveCollect(ActiveCollect activeCollect) {
        return activeCollectMapper.saveActiveCollect(activeCollect.getUserName(),activeCollect.getActiveId());
    }

    @Override
    public int deleteActiveCollect(int activeId) {
        List<ActiveCollect> activeCollectList = listActiveCollect(activeId);
        for (ActiveCollect activeCollect : activeCollectList){
            redisService.deleteKey2(activeCollect.getUserName(),String.valueOf(activeCollect.getActiveId()));
        }
        return activeCollectMapper.deleteActiveCollect(activeId);
    }

    @Override
    public ActiveCollect getById(String userName, int activeId) {
        return activeCollectMapper.getById(userName, activeId);
    }

    @Override
    public List<ActiveCollect> listActiveCollect(int activeId) {
        return activeCollectMapper.listActiveCollect(activeId);
    }

    @Override
    public List<Active> listActiveByUserNameByPage(String userName,int currentPage, int pageSize) {
        Map<String ,Object> map = new HashMap<>(5);
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
        return activeCollectMapper.listActiveByUserNameByPage(userName,map);
    }

    @Override
    public void transCollectFromRedis() {
        List<ActiveCollect> activeCollects = redisService.getCollectFromRedis();
        for (ActiveCollect activeCollect : activeCollects){
            ActiveCollect activeCollect1= getById(activeCollect.getUserName(),activeCollect.getActiveId());
            if (activeCollect1 == null){
                //数据库没有记录，存入数据库
                saveActiveCollect(activeCollect);
            }
        }
    }
}