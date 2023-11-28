package com.example.work.entity;

import lombok.Data;

import java.util.List;

@Data
public class Response<T> {
    String message;
    List<T> list;
}
