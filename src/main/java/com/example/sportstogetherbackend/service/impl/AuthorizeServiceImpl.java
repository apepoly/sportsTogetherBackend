package com.example.sportstogetherbackend.service.impl;

import com.example.sportstogetherbackend.entity.Role;
import com.example.sportstogetherbackend.mapper.RoleMapper;
import com.example.sportstogetherbackend.mapper.UserMapper;
import com.example.sportstogetherbackend.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Value("${spring.mail.username}")
    String from;
    @Resource
    MailSender mailSender;
    @Resource
    StringRedisTemplate template;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        com.example.sportstogetherbackend.entity.User user = userMapper.findUserByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        Role userRole = roleMapper.findRoleByUsernameOrEmail(user.getUsername());
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(userRole.getName())
                .build();
    }
    @Override
    public String sendValidateEmail(String email, String sessionId, boolean hasUser) {
        String key = "email:" + sessionId + ":" + email + ":" + hasUser;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 120)
                return "请求频繁，请稍后再试！";
        }
        com.example.sportstogetherbackend.entity.User user = userMapper.findUserByUsernameOrEmail(email);
        if (hasUser && user == null)
            return "没有此邮件的用户";
        if (!hasUser && user != null)
            return "此邮件已被其他用户注册";
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("验证码是:" + code);
        try {
            mailSender.send(message);
            template.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送异常，请查看邮箱是否有效";
        }
    }

    @Override
    public String validateAndRegister(String username, String password, String email, String code, String sessionId) {
        String key = "email:" + sessionId + ":" + email + ":false";
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null) return "验证码失效，请重新请求";
            if (s.equals(code)) {
                com.example.sportstogetherbackend.entity.User user = userMapper.findUserByUsernameOrEmail(username);
                if (user != null)
                    return "此用户名已被注册，请更换用户名";
                template.delete(key);
                password = encoder.encode(password);
                if (userMapper.insertUser(username, password, email , 1) > 0) {
                   return null;
                }
                return "内部错误，请联系管理员";
            } else {
                return "验证码错误，请检查后再提交";
            }
        } else {
            return "请先请求一封验证码邮件";
        }
    }

    @Override
    public String validateOnly(String email, String code, String sessionId) {
        String key = "email:" + sessionId + ":" + email + ":true";
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null) return "验证码失效，请重新请求";
            if (s.equals(code)) {
                return null;
            } else {
                return "验证码错误，请检查后再提交";
            }
        } else {
            return "请先请求一封验证码邮件";
        }
    }

    @Override
    public boolean resetPassword(String password, String email) {
        password = encoder.encode(password);
        return userMapper.resetPasswordByEmail(password, email) > 0;
    }

}
