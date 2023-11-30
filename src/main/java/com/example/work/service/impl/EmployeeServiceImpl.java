package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Employee;
import com.example.work.mapper.EmployeeMapper;
import com.example.work.service.IEmployeeService;
import jakarta.annotation.Resource;

import java.util.Arrays;
import java.util.List;

public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Resource
    private EmployeeMapper mapper;

    @Override
    public Employee selectEmpByEmpId(Long empId) {
        return mapper.selectById(new QueryWrapper<Employee>().lambda().eq(Employee::getEmpId,empId));
    }

    @Override
    public List<Employee> selectEmpList(Employee employee) {
        QueryWrapper<Employee> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(!employee.getEmpCode().isEmpty(),Employee::getEmpCode,employee.getEmpCode())
                .eq(!employee.getEmpName().isEmpty(),Employee::getEmpName,employee.getEmpName())
                .eq(!employee.getSex().isEmpty(),Employee::getSex,employee.getSex())
                .eq(!employee.getStatus().isEmpty(),Employee::getStatus,employee.getStatus())
                .eq(!employee.getEducation().isEmpty(),Employee::getEducation,employee.getEducation())
                .eq(!employee.getPhonenumber().isEmpty(),Employee::getPhonenumber,employee.getPhonenumber())
                .eq(!employee.getEmail().isEmpty(),Employee::getEmail,employee.getEmail());
        return mapper.selectList(wrapper);
    }

    @Override
    public Employee selectEmpByEmpCode(String empCode) {
        return mapper.selectOne(new QueryWrapper<Employee>().lambda().eq(Employee::getEmpCode,empCode));
    }

    @Override
    public int insertEmp(Employee Employee) {
        if(save(Employee)){
            return 0;
        };
        return 1;
    }

    @Override
    public int updateEmp(Employee employee) {
        UpdateWrapper<Employee> wrapper=new UpdateWrapper<>();
        wrapper.lambda()
                .eq(!employee.getEmpCode().isEmpty(),Employee::getEmpCode,employee.getEmpCode())
                .eq(!employee.getEmpName().isEmpty(),Employee::getEmpName,employee.getEmpName())
                .eq(!employee.getSex().isEmpty(),Employee::getSex,employee.getSex())
                .eq(!employee.getStatus().isEmpty(),Employee::getStatus,employee.getStatus())
                .eq(!employee.getEducation().isEmpty(),Employee::getEducation,employee.getEducation())
                .eq(!employee.getPhonenumber().isEmpty(),Employee::getPhonenumber,employee.getPhonenumber())
                .eq(!employee.getEmail().isEmpty(),Employee::getEmail,employee.getEmail());

        return mapper.update(employee,wrapper);
    }

    @Override
    public int deleteEmpByEmpIds(Long[] empIds) {
        return mapper.deleteBatchIds(Arrays.asList(empIds));
    }

    @Override
    public int deleteEmpByEmpId(Long empId) {
        return mapper.deleteById(empId);
    }

    @Override
    public String importEmp(List<Employee> empList, Boolean isUpdateSupport, String operName) {
        saveOrUpdateBatch(empList);
        return "success";
    }
}
