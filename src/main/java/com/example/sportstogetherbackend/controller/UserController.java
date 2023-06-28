package com.example.sportstogetherbackend.controller;

import com.example.sportstogetherbackend.entity.PageParam;
import com.example.sportstogetherbackend.entity.RestBean;
import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.entity.User;
import com.example.sportstogetherbackend.mapper.RoleMapper;
import com.example.sportstogetherbackend.mapper.UserMapper;
import com.example.sportstogetherbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    public RestBean<User> me(@SessionAttribute("user") User user) {
        return RestBean.success(user);
    }
    @PostMapping("/findUser")
    public RestBean<List<User>> findAll( @RequestParam("text") String text, @RequestParam("currPage") int currPage, @RequestParam("pageSize") int pageSize) {
        List<User> users = userService.findUser(text, currPage, pageSize);
        if (users != null)
            return RestBean.success(users);
        return RestBean.failure(500, null);
    }
    @GetMapping("/getUserCount")
    public RestBean<Integer> getUserCount() {
        return RestBean.success(userService.getUserCount());
    }
    @PostMapping("/findUserByUsernameOrEmail")
    public RestBean<User> findUserByUsernameOrEmail(@RequestParam("text") String text) {
        User user = userService.findUserByUsernameOrEmail(text);
        if (user != null)
            return RestBean.success(user);
        return RestBean.failure(500, null);
    }
    @PostMapping("/updateUser")
    public RestBean<String> updateUser(@RequestParam("id") Integer id, @RequestParam("username") String username, @RequestParam("email") String email) {
        String s = userService.updateUser(id, username, email);
        if (s == null)
            return RestBean.success("修改成功！");
        return RestBean.failure(500, s);
    }
    @PostMapping("/deleteUser")
    public RestBean<String> deleteUser(@RequestParam("id") Integer id) {
        String s = userService.deleteUser(id);
        if (s == null)
            return RestBean.success("删除成功！");
        return RestBean.failure(500, s);
    }
}
