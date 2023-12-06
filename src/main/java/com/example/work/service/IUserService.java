package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.User;

public interface IUserService extends IService<User> {
    int Login(User user);
    int Register(User user);
}
