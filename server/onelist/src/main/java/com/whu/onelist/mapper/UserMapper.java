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

    @Insert("insert into user (user_id,phone_num,email) values (#{userID},#{phoneNum},#{email})")
    int insert(User user);

    @Update("update user set password=#{pwd} where user_id=#{userID}")
    int updatePwd(@Param("userID")Long userID,@Param("pwd") String pwd);

    @Select("select user_id,phone_num,email from user where email=#{email} and password=#{pwd}")
    User loginByEmail(@Param("email")String email,@Param("pwd") String pwd);

    @Select("select user_id,phone_num,email from user where phone_num=#{phoneNum} and password=#{pwd}")
    User loginByPhone(@Param("phoneNum")String phoneNum,@Param("pwd") String pwd);
}
