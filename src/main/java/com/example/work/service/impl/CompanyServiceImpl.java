package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Company;
import com.example.work.mapper.CompanyMapper;
import com.example.work.service.ICompanyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
    @Resource
    private CompanyMapper mapper;
    @Override
    public int addCompany(Company company) {
        save(company);
        return 1;
    }

    @Override
    public int editCompany(Company company) {
        updateById(company);
        return 1;
    }

    @Override
    public int deleteCompany(Integer companyId) {
        return mapper.deleteById(companyId);
    }

    @Override
    public List<Company> getCompany(String companyName) {
        QueryWrapper<Company> wrapper=new QueryWrapper<>();
        if(companyName!=null){
            wrapper.lambda().eq(Company::getCompanyName,companyName);
        }
        return mapper.selectList(wrapper);
    }
}
