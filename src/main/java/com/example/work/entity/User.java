package com.example.work.entity;

import lombok.Data;

@Data
public class User {
    Integer userId;
    String username;
    String password;
    String type;
}
