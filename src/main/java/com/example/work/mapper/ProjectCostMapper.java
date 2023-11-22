package com.example.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.work.entity.ProjectCost;

import java.util.List;

public interface ProjectCostMapper
{

    /**
     * 核算项目成本
     *
     * @param projectIds 项目编号
     * @return 结果
     */
    public int accountProjectCostByProjectIds(Long[] projectIds);

    /**
     * 查询项目成本
     *
     * @param projectCostId 项目成本主键
     * @return 项目成本
     */
    public ProjectCost selectProjectCostByProjectCostId(Long projectCostId);

    /**
     * 查询项目成本
     *
     * @param projectId 项目主键
     * @return 项目成本
     */
    public ProjectCost selectProjectCostByProjectId(Long projectId);

    /**
     * 查询项目成本列表
     *
     * @param projectCost 项目成本
     * @return 项目成本集合
     */
    public List<ProjectCost> selectProjectCostList(ProjectCost projectCost);

    /**
     * 新增项目成本
     *
     * @param projectCost 项目成本
     * @return 结果
     */
    public int insertProjectCost(ProjectCost projectCost);

    /**
     * 修改项目成本
     *
     * @param projectCost 项目成本
     * @return 结果
     */
    public int updateProjectCost(ProjectCost projectCost);

    /**
     * 删除项目成本
     *
     * @param projectCostId 项目成本主键
     * @return 结果
     */
    public int deleteProjectCostByProjectCostId(Long projectCostId);

    /**
     * 批量删除项目成本
     *
     * @param projectCostIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectCostByProjectCostIds(Long[] projectCostIds);
}
