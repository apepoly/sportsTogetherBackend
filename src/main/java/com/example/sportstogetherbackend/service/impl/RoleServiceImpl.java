package com.example.sportstogetherbackend.service.impl;

import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.mapper.RoleMapper;
import com.example.sportstogetherbackend.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public Role findRoleByUsernameOrEmail(String text) {
        return roleMapper.findRoleByUsernameOrEmail(text);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public String updateRoleByID(Integer id, String name) {
        if (roleMapper.updateRoleByID(id,name) > 0) {
            return null;
        }
        return "修改失败!";
    }
}
