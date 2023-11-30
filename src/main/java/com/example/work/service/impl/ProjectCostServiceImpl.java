package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Employee;
import com.example.work.entity.Project;
import com.example.work.entity.ProjectCost;
import com.example.work.mapper.EmployeeMapper;
import com.example.work.mapper.ProjectCostMapper;
import com.example.work.mapper.ProjectMapper;
import com.example.work.service.IEmployeeService;
import com.example.work.service.IProjectCostService;
import jakarta.annotation.Resource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProjectCostServiceImpl extends ServiceImpl<ProjectCostMapper, ProjectCost> implements IProjectCostService {
    @Resource
    private ProjectCostMapper projectCostMapper;

    @Resource
    private ProjectMapper projectMapper;

    /**
     * 核算项目成本
     *
     * @param projectIds 项目ID
     * @return 结果
     */
    @Override
    public Boolean accountProjectCostByProjectIds(Long[] projectIds) {
        QueryWrapper<ProjectCost> wrapper=new QueryWrapper<>();
        for (int i=0;i<projectIds.length;i++){
            wrapper.lambda().eq(ProjectCost::getProjectId,projectIds[i]);
        }
        return  false;

    }

    /**
     * 查询项目成本
     *
     * @param projectCostId 项目成本主键
     * @return 项目成本
     */
    @Override
    public ProjectCost selectProjectCostByProjectCostId(Long projectCostId) {
        return projectCostMapper.selectById(projectCostId);
    }

    /**
     * 查询项目成本列表
     *
     * @param projectId 项目ID
     * @return 项目成本集合
     */
    @Override
    public List<ProjectCost> selectProjectCostList(Long projectId) {
        QueryWrapper<ProjectCost> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(ProjectCost::getProjectId,projectId);
        return projectCostMapper.selectList(wrapper);
    }

    /**
     * 新增项目成本
     *
     * @param projectCost 项目成本
     * @return 结果
     */
    @Override
    public int insertProjectCost(ProjectCost projectCost) {
        save(projectCost);
        return 1;
    }

    /**
     * 修改项目成本
     *
     * @param projectCost 项目成本
     * @return 结果
     */
    @Override
    public int updateProjectCost(ProjectCost projectCost) {
        updateById(projectCost);
        return 1;
    }

    /**
     * 批量删除项目成本
     *
     * @param projectCostIds 需要删除的项目成本主键集合
     * @return 结果
     */
    @Override
    public int deleteProjectCostByProjectCostIds(Long[] projectCostIds) {
        projectCostMapper.deleteBatchIds(Arrays.stream(projectCostIds).toList());
        return 1;
    }

    /**
     * 删除项目成本信息
     *
     * @param projectCostId 项目成本主键
     * @return 结果
     */
    @Override
    public int deleteProjectCostByProjectCostId(Long projectCostId) {
        projectCostMapper.deleteById(projectCostId);
        return 1;

    }
}