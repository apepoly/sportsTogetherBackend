package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAllUser();
    @Select("select * from user where username = #{text} or email = #{text}")
    User findUserByUsernameOrEmail(String text);
    @Insert("insert into user(username, password, email) values (#{username}, #{password}, #{email})")
    int insertUser(String username, String password, String email);
    @Update("update user set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);
    @Select("select * " +
            "from user u " +
            "right join user_role ur on u.id = ur.user_id " +
            "left join role on ur.role_id = role.id " +
            "where username = #{text} or email = #{text}")
    Role findRoleByUsernameOrEmail(String text);
    @Insert("insert into user_role(user_id, role_id) values(#{userID}, #{roleID})")
    int addRoleByUserIDAndRoleID(Integer userID, Integer roleID);
}
