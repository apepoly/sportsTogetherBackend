package com.example.sportstogetherbackend.service.impl;

import com.example.sportstogetherbackend.entity.PageParam;
import com.example.sportstogetherbackend.entity.RestBean;
import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;
import com.example.sportstogetherbackend.mapper.UserMapper;
import com.example.sportstogetherbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> findUser(String text, int currPage, int pageSize) {
        return userMapper.findUser(text,(currPage-1)*pageSize, pageSize);
    }

    @Override
    public User findUserByUsernameOrEmail(String text) {
        return userMapper.findUserByUsernameOrEmail(text);
    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public String updateUser(Integer id, String username, String email) {
        if (userMapper.updateUser(id, username, email) > 0) {
            return null;
        }
        return "修改失败！";
    }

    @Override
    public String deleteUser(Integer id) {
        if (userMapper.deleteUser(id) > 0) {
            return null;
        }
        return "删除失败！";
    }
}
