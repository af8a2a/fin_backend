package com.example.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.User;
import com.example.work.mapper.UserMapper;
import com.example.work.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper mapper;
    @Override
    public String Login(User user) {
        User selected=mapper.selectById(user.getUserId());
        if(selected!=null&&selected.getPassword().equals(user.getPassword())){
            return "success";
        }
        return "error";
    }

    @Override
    public String Register(User user) {
        return null;
    }
}
