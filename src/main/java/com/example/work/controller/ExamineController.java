package com.example.work.controller;

import com.example.work.entity.Examine;
import com.example.work.entity.FinWages;
import com.example.work.entity.Response;
import com.example.work.service.IExamineService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/financial/examine")
public class ExamineController {
    @Resource
    private IExamineService service;
    @PostMapping("/list")
    public Response<Examine> list(@RequestBody Examine examine)
    {
        Response<Examine> response=new Response<>();
        response.setList(service.selectExamineList(examine));
        if(response.getList().isEmpty()){
        }else{
            response.setMessage("success");
        }
        return response;
    }
    @PostMapping("/search")
    public Response<Map<String, Object>> search(@RequestBody Examine examine)
    {
        Response<Map<String, Object>> response=new Response<>();
        response.setList(service.selectCompany(examine));
        if(response.getList().isEmpty()){
        }else{
            response.setMessage("success");
        }
        return response;
    }

    @PostMapping("/add")
    public Response<Examine> add(@RequestBody Examine examine)
    {
        Response<Examine> response=new Response<>();
        if(service.addExamine(examine)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping("/finish")
    public Response<Examine> finish(@RequestBody Examine examine)
    {
        Response<Examine> response=new Response<>();
        if(service.finishExamine(examine)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }
}
