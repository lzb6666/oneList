package com.whu.onelist.autoadd;

import com.whu.onelist.autoadd.patternhandlers.AddPatternHandler;
import com.whu.onelist.autoadd.patternhandlers.NumberPatternHandler;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by zhong
 * com.whu.onelist.autoadd
 * Date 2019/5/10
 */
@Component
public class DateHandler {
    private static final Logger log= LoggerFactory.getLogger(DateHandler.class);
    private HashMap setMap;
    private HashMap addMap;
    private Map<Integer,DatePatternHandler> handlerMap;

    DateHandler() throws IOException {
        Dic dic=new Dic();
        setMap=dic.dicReader("setDic");
        addMap=dic.dicReader("addDic");
        handlerMap=new ConcurrentHashMap<>();
    }

    private DatePatternHandler getHandler(Integer type){
        if (handlerMap.containsKey(type)){
            return handlerMap.get(type);
        }else {
            DatePatternHandler handler=null;
            switch (type){
                case 1:
                    handler=new NumberPatternHandler();
                    break;
                case 2:
                    handler=new AddPatternHandler();
                    break;
            }
            handlerMap.put(type,handler);
            return handler;

        }

    }

    public Timestamp process(String content){
        Calendar calendar=new GregorianCalendar();
        for (Object key:setMap.keySet()
                ) {
            Pattern pattern=PatternFactory.getPattern((String) key);
            Matcher matcher=pattern.matcher(content);
            if (matcher.find()){
                Pair pair=(Pair)setMap.get(key);
                int field= (int) pair.getKey();
                String value= (String) pair.getValue();
                if (value.equals("\\d+")){
                    /*Pattern p=PatternFactory.getPattern("\\d+");
                    Matcher m=p.matcher(matcher.group());
                    if (m.find()){
                        calendar.set(field,Integer.valueOf(m.group()));
                    }*/
                    int time=getHandler(1).handler(matcher.group(),value);
                    calendar.set(field,time);
                }else if (value.contains("\\d+")){
/*                    Pattern p=PatternFactory.getPattern("\\d+");
                    Matcher m=p.matcher(matcher.group());
                    if (m.find()){
                        String[] opt=value.split("\\+");
                        int time=Integer.valueOf(m.group());
                        if (time<12){
                            time+=Integer.valueOf(opt[0]);
                        }
                        calendar.set(field,time);
                    }*/
                    int time=getHandler(2).handler(matcher.group(),value);
                    calendar.set(field,time);
                }
                else {
                    calendar.set(field,Integer.valueOf(value));
                }
                setDefault(field,calendar);
            }
        }

        for (Object key:addMap.keySet()
                ) {
            Pattern pattern=PatternFactory.getPattern((String) key);
            Matcher matcher=pattern.matcher(content);
            if (matcher.find()){
                Pair pair=(Pair)addMap.get(key);
                int field= (int) pair.getKey();
                String value= (String) pair.getValue();
                if (value.equals("\\d+")){
                    /*Pattern p=Pattern.compile("\\d+");
                    Matcher m=p.matcher(matcher.group());
                    if (m.find()){
                        calendar.add(field,Integer.valueOf(m.group()));
                    }*/
                    int time=handlerMap.get(1).handler(matcher.group(),value);
                    calendar.add(field,time);
                }else {
                    calendar.add(field,Integer.valueOf(value));
                }
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }

    private void setDefault(int field,Calendar calendar){
        switch (field){
            case Calendar.DATE:
                calendar.set(Calendar.HOUR,7);
            case Calendar.HOUR:
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);
                break;
        }
    }
}
