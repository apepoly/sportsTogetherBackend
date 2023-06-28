package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select * " +
            "from user u " +
            "right join user_role ur on u.id = ur.user_id " +
            "left join role on ur.role_id = role.id " +
            "where username = #{text} or email = #{text}")
    Role findRoleByUsernameOrEmail(String text);
    @Insert("insert into user_role(user_id, role_id) values(#{userID}, #{roleID})")
    int addRoleByUserIDAndRoleID(Integer userID, Integer roleID);
    @Select("select * from role")
    List<Role> getRoles();
    @Update("update role set name = #{name} where id={id}")
    int updateRoleByID(Integer id,String name);
}
