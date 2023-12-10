package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {
    int Login(User user);
    int Register(User user);

    List<User> search(User user);

    int Update(User user);

    int Delete(User user);

}
