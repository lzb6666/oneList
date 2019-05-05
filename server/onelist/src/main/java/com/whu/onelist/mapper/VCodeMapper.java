package com.whu.onelist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.mapper
 * @date:2019/4/8
 */
@Repository
public interface VCodeMapper {

    @Insert("insert into vcode values (#{registry},#{vcode},#{type},#{expirationDate}) ")
    int insert(@Param("registry")String registry,@Param("vcode") String vcode,@Param("type") Integer type,@Param("expirationDate") Timestamp expirationDate);

    //@Select("select count(1) from vcode where registry=#{registry} and vcode=#{vcode} and type=#{type} and expiration_date>#{now} limit 1")
    @Select("select count(1) from vcode where registry=#{registry} and vcode=#{vcode} and type=#{type} limit 1")
    Integer select(@Param("registry")String registry,@Param("vcode")String vcode,@Param("type")Integer type,@Param("now")Timestamp now);

    @Delete("delete from vcode where registry=#{registry} and type=#{type}")
    int delete(@Param("registry")String registry,@Param("type")Integer type);


}
