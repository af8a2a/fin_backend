package com.example.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.work.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
//    /**
//     * 查询员工
//     *
//     * @param empId 员工主键
//     * @return 员工
//     */
//    public Employee selectEmpByEmpId(Long empId);
//
//    /**
//     * 查询员工列表
//     *
//     * @param Employee 员工
//     * @return 员工集合
//     */
//    public List<Employee> selectEmpList(Employee Employee);
//
//    /**
//     * 通过员工号查询员工
//     *
//     * @param empCode 员工号
//     * @return 员工对象信息
//     */
//    public Employee selectEmpByEmpCode(String empCode);
//
//    /**
//     * 新增员工
//     *
//     * @param Employee 员工
//     * @return 结果
//     */
//    public int insertEmp(Employee Employee);
//
//    /**
//     * 修改员工
//     *
//     * @param Employee 员工
//     * @return 结果
//     */
//    public int updateEmp(Employee Employee);
//
//    /**
//     * 删除员工
//     *
//     * @param empId 员工主键
//     * @return 结果
//     */
//    public int deleteEmpByEmpId(Long empId);
//
//    /**
//     * 批量删除员工
//     *
//     * @param empIds 需要删除的数据主键集合
//     * @return 结果
//     */
//    public int deleteEmpByEmpIds(Long[] empIds);
}
