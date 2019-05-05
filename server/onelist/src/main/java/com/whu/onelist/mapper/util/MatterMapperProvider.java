package com.whu.onelist.mapper.util;

import com.whu.onelist.vo.Matter;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.mapper.util
 * @date:2019/4/23
 */
public class MatterMapperProvider {
    public String updateSql(Matter matter){

        return new SQL(){
            {
                UPDATE("matter");
                if (matter.getCaption()!=null){
                    SET("caption=#{caption}");
                }
                if (matter.getDetail()!=null){
                    SET("detail=#{detail}");
                }
                if (matter.getRemindTime()!=null){
                    SET("remind_time=#{remindTime}");
                }
                if (matter.getRemindInterval()!=null){
                    SET("remind_interval=#{remindInterval}");
                }
                if (matter.getPriority()!=0){
                    SET("priority=#{#priority}");
                }
                WHERE("matter_id=#{matterID}");
                WHERE("user_id=#{userID}");
            }
        }.toString();
    }
}
