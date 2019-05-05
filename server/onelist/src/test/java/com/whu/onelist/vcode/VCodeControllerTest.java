package com.whu.onelist.vcode;

import com.whu.onelist.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * create by zhong
 * com.whu.onelist.vcode
 * Date 2019/5/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VCodeControllerTest {

    @Autowired
    private VCodeService service;

    @Test
    public void vlf(){
        User user= service.vlfRegistryCode("123456","123456","1234");
    }
}
