package com.example.work.controller;

import com.example.work.entity.Response;
import com.example.work.entity.User;
import com.example.work.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial/user")

public class UserController {
    @Resource
    private IUserService service;
    @PostMapping("/login")
    public Response<User> login(@RequestBody User user){
        Response<User> response=new Response<>();

        if(service.Login(user)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }
    @PostMapping("/register")
    public Response<User> register(@RequestBody User user){
        Response<User> response=new Response<>();
        if(service.Register(user)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }
    @PostMapping("/select")
    public Response<User> search(@RequestBody User user){
        Response<User> response=new Response<>();
        response.setList(service.search(user));
        return response;
    }
    @PostMapping("/update")
    public Response<User> update(@RequestBody User user){
        Response<User> response=new Response<>();
        if(service.Update(user)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }
    @PostMapping("/delete")
    public Response<User> delete(@RequestBody User user){
        Response<User> response=new Response<>();
        if(service.Delete(user)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }
}
