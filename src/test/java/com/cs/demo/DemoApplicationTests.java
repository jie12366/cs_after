package com.cs.demo;

import com.cs.demo.mapper.ActivePictureMapper;
import com.cs.demo.service.impl.UploadServiceImpl;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ActivePictureMapper pictureMapper;

    @Autowired
    UploadServiceImpl uploadService;

    @Test
    public void contextLoads() throws QiniuException {
        uploadService.deleteFile("Frcew0ZIFmI4qAyRttaxIKsnUFEN");
    }

}
