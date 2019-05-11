package com.whu.onelist.autoadd.patternhandlers;

import com.whu.onelist.autoadd.DatePatternHandler;
import com.whu.onelist.autoadd.PatternFactory;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by zhong
 * com.whu.onelist.autoadd.patternhandlers
 * Date 2019/5/11
 */
public class NumberPatternHandler implements DatePatternHandler {
    @Override
    public int handler(String content, String pattern) {
        Pattern p=PatternFactory.getPattern("\\d+");
        Matcher m=p.matcher(content);
        if (m.find()){
            return Integer.valueOf(m.group());
        }else {
            return -1;
        }
    }
}
