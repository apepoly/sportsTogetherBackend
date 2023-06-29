package com.example.sportstogetherbackend.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private  String email;
    private String password;
    private Integer roleID;
}
