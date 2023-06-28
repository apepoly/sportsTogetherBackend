package com.example.sportstogetherbackend.entity;

import lombok.Data;

@Data
public class PageParam {
    private Integer pageNum;//待查询的页码
    private Integer pageSize;//每页显示的条目数
}
