package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.Employee;

import java.util.List;

public interface IEmployeeService extends IService<Employee> {
    /**
     * 查询员工
     *
     * @param empId 员工主键
     * @return 员工
     */
    public Employee selectEmpByEmpId(Long empId);

    /**
     * 查询员工列表
     *
     * @param Employee 员工
     * @return 员工集合
     */
    public List<Employee> selectEmpList(Employee Employee);

    /**
     * 通过员工号查询员工
     *
     * @param empCode 员工号
     * @return 员工对象信息
     */
    public Employee selectEmpByEmpCode(String empCode);

    /**
     * 新增员工
     *
     * @param Employee 员工
     * @return 结果
     */
    public int insertEmp(Employee Employee);

    /**
     * 修改员工
     *
     * @param Employee 员工
     * @return 结果
     */
    public int updateEmp(Employee Employee);

    /**
     * 批量删除员工
     *
     * @param empIds 需要删除的员工主键集合
     * @return 结果
     */
    public int deleteEmpByEmpIds(Long[] empIds);

    /**
     * 删除员工信息
     *
     * @param empId 员工主键
     * @return 结果
     */
    public int deleteEmpByEmpId(Long empId);

    /**
     * 导入员工数据
     *
     * @param empList 员工数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importEmp(List<Employee> empList, Boolean isUpdateSupport, String operName);
}
