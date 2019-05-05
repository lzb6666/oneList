package com.whu.onelist.matter;

import com.whu.onelist.mapper.MatterMapper;
import com.whu.onelist.util.SnowFlake;
import com.whu.onelist.vo.Matter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.matter
 * @date:2019/4/23
 */
@Service
public class MatterService {
    //setter注入开始
    @Autowired
    public void setMatterMapper(MatterMapper matterMapper) {
        this.matterMapper = matterMapper;
    }


    //setter注入结束

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

    List<Matter> selectMattersByDate(Long startTime,Long endTime,Long userID){
        return matterMapper.selectMattersByDate(startTime,endTime,userID);
    }
}
