package com.whu.onelist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.mapper
 * @date:2019/4/8
 */
@Repository
public interface VCodeMapper {

    @Insert("insert into vcode values (#{registry},#{vcode},#{type},#{expirationDate}) ")
    int insert(@Param("registry")String registry,@Param("vcode") String vcode,@Param("type") Integer type,@Param("expirationDate") Long expirationDate);

    @Select("select type from vcode where registry=#{registry} and vcode=#{vcode} and type=#{type} and expirationDate>#{now} limit 1")
    int select(@Param("registry")String registry,@Param("vcode")String vcode,@Param("type")Integer type,@Param("now")Long now);

    @Delete("delete * from vcode where registry=#{registry} and type=#{type}")
    int delete(@Param("registry")String registry,@Param("type")Integer type);


}
