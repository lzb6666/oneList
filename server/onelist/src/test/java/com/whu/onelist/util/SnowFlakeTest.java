package com.whu.onelist.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnowFlakeTest {

    @Test
    public void nextId(){
        System.out.println(SnowFlake.nextId());
        System.out.println(SnowFlake.getDataCenterId());
    }
}
