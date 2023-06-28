package com.example.sportstogetherbackend.service;

import com.example.sportstogetherbackend.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByUsernameOrEmail(String text);
    List<Role> getRoles();
    String updateRoleByID(Integer id,String name);
}
