package com.cs.demo.service.impl;

import com.cs.demo.entity.StudentMessage;
import com.cs.demo.mapper.StudentMessageMapper;
import com.cs.demo.service.StudentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/8 19:09
 */
@Service
public class StudentMessageServiceImpl implements StudentMessageService {

    @Autowired
    StudentMessageMapper studentMessageMapper;

    @Override
    public int saveStudentMessage(String userName,String nickName, String avatar, String sex, String name, String introduction,
                                  String address, String school, String society, String social, String socialNumber) {
        return studentMessageMapper.saveStudentMessage(userName,nickName, avatar, sex, name, introduction, address, school,
                society, social, socialNumber);
    }

    @Override
    public int updateStudentMessage(String nickName, String avatar, String sex, String name, String introduction,
                                    String address, String school, String society, String social, String socialNumber, String userName) {
        return studentMessageMapper.updateStudentMessage(nickName, avatar, sex, name, introduction, address, school,
                society, social, socialNumber, userName);
    }

    @Override
    public StudentMessage getStudentMessageById(String userName) {
        return studentMessageMapper.getStudentMessageById(userName);
    }
}