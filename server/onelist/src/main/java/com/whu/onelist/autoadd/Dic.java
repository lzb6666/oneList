package com.whu.onelist.autoadd;

import javafx.util.Pair;

import java.io.*;
import java.util.HashMap;

/**
 * create by zhong
 * com.whu.onelist.autoadd
 * Date 2019/5/10
 */
public class Dic {
    public HashMap dicReader(String path) throws IOException {
        HashMap<String,Pair> map=new HashMap<String,Pair>();
        BufferedReader reader=new BufferedReader(new FileReader(getClass().getClassLoader().getResource(path).getFile()));
        String line=null;
        while ((line=reader.readLine())!=null){
            String keys[]=line.split(" ");
            if (keys.length!=3){
                continue;
            }
            map.put(keys[0],new Pair<>(Integer.valueOf(keys[1]),keys[2]));
        }
        return map;
    }
}
