package com.example.sportstogetherbackend.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String email;
    String password;
}
