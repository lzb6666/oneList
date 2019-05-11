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
    public HashMap dicReader(String fileName) throws IOException {
        HashMap<String,Pair> map=new HashMap<>();
        /*InputStream path=getClass().getClassLoader().getResourceAsStream(fileName);
        InputStreamReader reader=new InputStreamReader(path);*/
        //BufferedReader reader=new BufferedReader(new FileReader(getClass().getClassLoader().getResource(fileName).getFile()));
        BufferedReader reader=new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
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
