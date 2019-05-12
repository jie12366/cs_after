package com.cs.demo;

import com.cs.demo.entity.Active;
import com.cs.demo.service.impl.ActiveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ActiveServiceImpl activeService;

    @Test
    public void contextLoads() {
        Active active = new Active();
        active.setTitle("测试");
        activeService.saveActive(active);
        System.out.println("id = " + activeService.getMaxId());
    }

}
