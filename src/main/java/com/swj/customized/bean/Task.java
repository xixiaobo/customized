package com.swj.customized.bean;

import lombok.Data;

@Data
public class Task {
    private Integer id;

    private String title;

    private String userid;

    private String status;

    private String createtime;

    private String content;

}