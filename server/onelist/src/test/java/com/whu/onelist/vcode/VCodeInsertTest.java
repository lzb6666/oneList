package com.whu.onelist.vcode;

import com.whu.onelist.mapper.VCodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * create by zhong
 * com.whu.onelist.vcode
 * Date 2019/5/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VCodeInsertTest {
    @Autowired
    private VCodeMapper mapper;

    @Test
    public void insertTest(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        //mapper.insert("123456","1234",1,timestamp);
    }

    @Test
    public void timeStamp(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(timestamp);
    }

}
