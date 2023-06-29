package com.example.sportstogetherbackend.service;

import com.example.sportstogetherbackend.entity.User;

import java.util.List;

public interface UserService {
    List<User> findUser(String text, int currPage, int pageSize);
    User findUserByUsernameOrEmail(String text);
    int getUserCount();
    String updateUser(Integer id, String username, String email);
    String deleteUser(Integer id);
    String insertUser(String username, String password, String email, Integer roleID);
    String setGym(Integer userID, Integer gymID);
}
