package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username like concat(#{text},'%') or email like concat(#{text}, '%') limit #{currIndex}, #{pageSize}")
    List<User> findUser(String text, int currIndex, int pageSize);
    @Select("select * from user where username = #{text} or email = #{text}")
    User findUserByUsernameOrEmail(String text);
    @Insert("insert into user(username, password, email) values (#{username}, #{password}, #{email})")
    int insertUser(String username, String password, String email);
    @Update("update user set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);
    @Select("select count(*) from user;")
    int getUserCount();
    @Update("update user set username = #{username}, email = #{email} where id = #{id}")
    int updateUser(Integer id, String username, String email);
    @Delete("delete from user where id=#{id}")
    int deleteUser(Integer id);
}
