package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select * from user right join role on user.role_id = role.id where user.username = #{text} or email = #{text}")
    Role findRoleByUsernameOrEmail(String text);
    @Select("select * from role")
    List<Role> getRoles();
    @Update("update user set role_id = #{roleID} where username=#{username}")
    int updateRoleByID(Integer roleID, String username);
}
