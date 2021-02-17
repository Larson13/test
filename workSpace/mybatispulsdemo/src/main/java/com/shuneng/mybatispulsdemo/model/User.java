package com.shuneng.mybatispulsdemo.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
    private String email;
}
