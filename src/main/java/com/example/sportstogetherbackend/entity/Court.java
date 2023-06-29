package com.example.sportstogetherbackend.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Court {
    private Integer id;
    private String category;
    private String no;
    private Float price;
    private Integer number;
    private String startTime;
    private String endTime;
    private Integer gymID;
}
