package com.example.sportstogetherbackend.controller;

import com.example.sportstogetherbackend.entity.RestBean;
import com.example.sportstogetherbackend.entity.User;
import com.example.sportstogetherbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/me")
    public RestBean<User> me(@SessionAttribute("user") User user) {
        return RestBean.success(user);
    }
    @GetMapping("/findAll")
    public RestBean<List<User>> findAll() {
        return RestBean.success(userMapper.findAllUser());
    }
}
