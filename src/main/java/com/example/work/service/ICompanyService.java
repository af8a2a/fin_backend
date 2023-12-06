package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.Company;

import java.util.List;

public interface ICompanyService extends IService<Company> {
    int addCompany(Company company);
    int editCompany(Company company);

    int deleteCompany(Integer companyId);

    List<Company> getCompany(String companyName);

}
