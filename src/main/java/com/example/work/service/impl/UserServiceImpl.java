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
    public int Login(User user) {
        User selected=mapper.selectById(user.getUserId());
        if(selected!=null&&selected.getPassword().equals(user.getPassword())){
            return 1;
        }
        return 0;
    }

    @Override
    public int Register(User user) {
        if(user.getCompany()==null||user.getUsername()==null||user.getPassword()==null){
            return 0;
        }else{
            save(user);
        }
        return 1;
    }
}
