package com.example.work.controller;

import com.example.work.entity.Company;
import com.example.work.entity.FinWages;
import com.example.work.entity.Response;
import com.example.work.service.ICompanyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/financial/company")

public class CompanyController {
    @Resource
    private ICompanyService service;

    @GetMapping("/list")
    public Response<Company> list(String companyName){
        Response<Company> response=new Response<>();
        response.setList(service.getCompany(companyName));
        return response;
    }

    @PostMapping("/add")
    public Response<Company> add(Company company){
        Response<Company> response=new Response<>();
        if(service.addCompany(company)!=0){
            response.setMessage("success");
        }
        return response;
    }
    @PostMapping("/edit")
    public Response<Company> edit(Company company){
        Response<Company> response=new Response<>();
        if(service.editCompany(company)!=0){
            response.setMessage("success");
        }
        return response;
    }
    @PostMapping("/delete")
    public Response<Company> delete(Integer companyId){
        Response<Company> response=new Response<>();
        if(service.deleteCompany(companyId)!=0){
            response.setMessage("success");
        }
        return response;
    }
}
