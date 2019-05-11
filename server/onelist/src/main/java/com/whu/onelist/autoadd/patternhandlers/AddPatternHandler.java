package com.whu.onelist.autoadd.patternhandlers;

import com.whu.onelist.autoadd.DatePatternHandler;
import com.whu.onelist.autoadd.PatternFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by zhong
 * com.whu.onelist.autoadd.patternhandlers
 * Date 2019/5/11
 */
public class AddPatternHandler implements DatePatternHandler {
    @Override
    public int handler(String content, String pattern) {
        Pattern p= PatternFactory.getPattern("\\d+");
        Matcher m=p.matcher(content);
        if (m.find()){
            String[] opt=pattern.split("\\+");
            int time=Integer.valueOf(m.group());
            if (time<12){
                time+=Integer.valueOf(opt[0]);
            }
            return time;
        }
        return -1;
    }
}
