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
            startTime=new Timestamp(0);
            endTime=new Timestamp(System.currentTimeMillis());
        }
        return matterMapper.selectMattersByDate(startTime,endTime,userID);
    }

    Matter autoAdd(String content,Long userID){
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
}
