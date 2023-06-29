package com.example.sportstogetherbackend;

import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;
import com.example.sportstogetherbackend.mapper.RoleMapper;
import com.example.sportstogetherbackend.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SportsTogetherBackendApplicationTests {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Test
    void contextLoads() {
        Role role = roleMapper.findRoleByUsernameOrEmail("apepoly");
        System.out.println(role);
    }
    @Test
    void passwd() {
        com.example.sportstogetherbackend.entity.User user = userMapper.findUserByUsernameOrEmail("user1");
        System.out.println(user);
    }

}
