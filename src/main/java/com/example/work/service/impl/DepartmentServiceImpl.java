package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Department;
import com.example.work.entity.Employee;
import com.example.work.mapper.DepartmentMapper;
import com.example.work.mapper.EmployeeMapper;
import com.example.work.service.IDepartmentService;
import com.example.work.service.IEmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Resource
    private DepartmentMapper mapper;
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<Department> selectDeptList(Department dept) {
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(!dept.getDeptName().isEmpty(),Department::getDeptName,dept.getDeptName())
                .eq(!dept.getStatus().isEmpty(),Department::getStatus,dept.getStatus());
        return mapper.selectList(wrapper);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    @Deprecated

    public List<Department> buildDeptTree(List<Department> depts) {
        return null;
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    @Deprecated

    public List<Long> selectDeptListByRoleId(Long roleId) {
        return null;
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Department selectDeptById(Long deptId) {
        return mapper.selectById(deptId);
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    @Deprecated
    public int selectNormalChildrenDeptById(Long deptId) {
        return 0;
    }

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    @Deprecated

    public boolean hasChildByDeptId(Long deptId) {
        return false;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return mapper.selectById(deptId)==null;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(Department dept) {
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(Department::getDeptName,dept.getDeptName());
        //todo
        if(mapper.selectList(wrapper).size()<2){
            return "success";
        };
        return "no";
    }

    /**
     * 校验部门是否有数据权限
     *
     * @param deptId 部门id
     */
    @Override
    public void checkDeptDataScope(Long deptId) {

    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(Department dept) {
        save(dept);
        return 1;
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(Department dept) {
        updateById(dept);
        return 1;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        mapper.deleteById(deptId);
        return 0;
    }
}
