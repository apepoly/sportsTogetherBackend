package com.example.sportstogetherbackend.service;

import com.example.sportstogetherbackend.entity.PageParam;
import com.example.sportstogetherbackend.entity.RestBean;
import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;

import java.util.List;

public interface UserService {
    List<User> findUser(String text, int currPage, int pageSize);
    User findUserByUsernameOrEmail(String text);
    int getUserCount();
    String updateUser(Integer id, String username, String email);
    String deleteUser(Integer id);
}
