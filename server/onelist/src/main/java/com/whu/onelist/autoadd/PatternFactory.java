package com.whu.onelist.autoadd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * create by zhong
 * com.whu.onelist.autoadd
 * Date 2019/5/11
 */
public class PatternFactory {
    private static Map<String,Pattern> patternMap=new ConcurrentHashMap<String,Pattern>();

     public static Pattern getPattern(String pattern){
        if (patternMap.containsKey(pattern)){
            return patternMap.get(pattern);
        }else {
            Pattern p=Pattern.compile(pattern);
            patternMap.put(pattern,p);
            return p;
        }
    }
}
