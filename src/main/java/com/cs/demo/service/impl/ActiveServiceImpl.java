package com.cs.demo.service.impl;

import com.cs.demo.entity.Active;
import com.cs.demo.mapper.ActiveMapper;
import com.cs.demo.mapper.ActivePictureMapper;
import com.cs.demo.service.ActiveService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/5 16:23
 */
@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;
    @Autowired
    ActivePictureMapper pictureMapper;
    @Autowired
    UploadServiceImpl uploadService;

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
    public List<Active> listActiveByPage() {
        return activeMapper.listActiveByPage();
    }

    @Override
    public int deleteActive(int id) {
        List<String > picList = pictureMapper.getPictureById(id);
        //从数据库中将图片链接删除
        pictureMapper.deletePictureById(id);
        try {
            for (String pic : picList){
                //从七牛云中把对应的文件删除
                String key = pic.substring(24);
                uploadService.deleteFile(key);
            }
            //删除active表中的附件
            String annex = getActiveById(id).getAnnex().substring(24);
            uploadService.deleteFile(annex);
        }catch (QiniuException e){
            e.printStackTrace();
        }
        return activeMapper.deleteActive(id);
    }
}