package com.example.sportstogetherbackend.controller;

import com.example.sportstogetherbackend.entity.RestBean;
import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.mapper.RoleMapper;
import com.example.sportstogetherbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/findRoleByUsernameOrEmail")
    public RestBean<Role> findRoleByUsernameOrEmail(@RequestParam("text") String text) {
        return RestBean.success(roleService.findRoleByUsernameOrEmail(text));
    }
    @GetMapping("/getRoles")
    public RestBean<List<Role>> getRoles() {
        return RestBean.success(roleService.getRoles());
    }
    @PostMapping("/updateRole")
    public RestBean<String> updateRole(@RequestParam("role") String role, @RequestParam("username") String username) {
        int roleID = switch (role) {
            case "超级管理员" -> 3;
            case "管理员" -> 2;
            case "用户" -> 1;
            default -> -1;
        };
        String s = roleService.updateRoleByID(roleID, username);
        if (s ==null)
          return RestBean.success("修改成功");
        return RestBean.failure(400, s);
    }
}
