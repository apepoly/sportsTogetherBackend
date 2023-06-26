package com.example.sportstogetherbackend.entity;

import lombok.Data;

@Data
public class GymAdmin {
    Integer id;
    Integer gymID;
    String username;
    String email;
    String password;

}
