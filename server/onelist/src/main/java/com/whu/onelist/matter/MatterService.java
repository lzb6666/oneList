package com.whu.onelist.matter;

import com.whu.onelist.autoadd.DateHandler;
import com.whu.onelist.mapper.MatterMapper;
import com.whu.onelist.util.SnowFlake;
import com.whu.onelist.vo.Matter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.matter
 * @date:2019/4/23
 */
@Service
public class MatterService {
    private static final org.slf4j.Logger log= LoggerFactory.getLogger(MatterService.class);
    //setter注入开始
    @Autowired
    public void setMatterMapper(MatterMapper matterMapper) {
        this.matterMapper = matterMapper;
    }
    @Autowired
    public void setHandler(DateHandler handler) {
        this.handler = handler;
    }

    //setter注入结束
    private DateHandler handler;
    private MatterMapper matterMapper;

    boolean addMatter(Matter matter){
        matter.setMatterID(SnowFlake.nextId());
        return matterMapper.insert(matter)==1;
    }

    boolean updateMatter(Matter matter){
        return matterMapper.update(matter)==1;
    }

    boolean deleteMatter(Long matterID,Long userID){
        return matterMapper.delete(matterID,userID)==1;
    }

    Matter selectMatterByID(Long matterID){
        return matterMapper.select(matterID);
    }

    List<Matter> selectMattersByDate(Timestamp startTime, Timestamp endTime, Long userID){
        if (startTime==null&&endTime==null){
            return matterMapper.selectMatters(userID);
        }
        return matterMapper.selectMattersByDate(startTime,endTime,userID);
    }

    Matter autoAdd(String content,Long userID){
        content=convert(content);
        Matter matter=new Matter();
        matter.setRemindTime(handler.process(content));
        matter.setDetail(content);
        matter.setUserID(userID);
        matter.setMatterID(SnowFlake.nextId());
        if (matterMapper.insert(matter)==1){
            return matter;
        }else {
            log.error(content);
            return null;
        }
    }

    String convert(String str)
    {
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++)
        {
            char temp = 'n';
            char current = chars[i];
            switch (current)
            {
                case '一':
                    temp = '1';
                    break;
                case '二':
                    temp = '2';
                    break;
                case '三':
                    temp = '3';
                    break;
                case '四':
                    temp = '4';
                    break;
                case '五':
                    temp = '5';
                    break;
                case '六':
                    temp = '6';
                    break;
                case '七':
                    temp = '7';
                    break;
                case '八':
                    temp = '8';
                    break;
                case '九':
                    temp = '9';
                    break;
                case '零':
                    temp = '0';
                    break;
                case '十':
                    if(Character.isDigit(chars[i-1]))
                    {
                        for(int j = 0; j < chars.length - i - 1; j++)
                        {
                            chars[i + j] = chars[i + j + 1];
                        }
                        chars[chars.length - 1] = ' ';
                        i--;
                        break;
                    }
                    else
                    {
                        temp = '1';
                        break;
                    }
                default:
                    temp = 'n';
            }
            if(temp != 'n')
            {
                chars[i] = temp;
            }
        }

        String result = "";
        for (int k = 0; k < chars.length; k++){
            if(chars[k] != ' ')
                result += chars[k];
        }
        return result;
    }
}
