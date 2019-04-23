package com.whu.onelist.mapper;

import com.whu.onelist.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.mapper
 * @date:2019/4/23
 */
@Repository
public interface UserMapper {

    @Insert("insert into user (user_id,phoneNum,email) values {#{userID},#{phoneNum},#{email}}")
    int insert(User user);

    @Update("update user set password=#{pwd} where userID=#{userID}")
    int updatePwd(@Param("userID")Long userID,@Param("pwd") String pwd);

    @Select("select user_id,phoneNum,email from user where user_id=#{userID} and password=#{pwd}")
    User login(@Param("userID")Long userID,@Param("pwd") String pwd);
}
