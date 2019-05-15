package com.whu.onelist.mapper;

import com.whu.onelist.vo.Matter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.mapper
 * @date:2019/4/23
 */
@Repository
public interface MatterMapper {

    @Insert("insert into matter " +"(matter_id,user_id,caption,detail,remind_time,remind_interval,priority,status)"+
            "values (#{matterID},#{userID},#{caption},#{detail},#{remindTime},#{remindInterval},#{priority},#{status})")
    int insert(Matter matter);

    @UpdateProvider(type=com.whu.onelist.mapper.util.MatterMapperProvider.class,method = "updateSql")
    int update(Matter matter);

    @Delete("delete from matter where matter_id=#{matterID} and user_id=#{userID}")
    int delete(@Param("matterID") Long matterID,@Param("userID") Long userID);

    @Select("select matter_id,user_id,caption,detail,remind_time,remind_interval,priority,status from matter where matter_id=#{matterID}")
    Matter select(Long matterID);

    @Select("select matter_id,user_id,caption,detail,remind_time,remind_interval,priority,status from matter where " +
            "user_id=#{userID} and remind_time > #{startTime} and remind_time <#{endTime}")
    List<Matter> selectMattersByDate(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime, @Param("userID") Long userID);

    @Select("select matter_id,user_id,caption,detail,remind_time,remind_interval,priority,status from matter where " +
            "user_id=#{userID}")
    List<Matter> selectMatters(Long userID);
}
