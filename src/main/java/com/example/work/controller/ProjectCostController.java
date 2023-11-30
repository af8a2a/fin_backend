package com.example.work.controller;

import com.baomidou.mybatisplus.core.injector.methods.UpdateById;
import com.example.work.entity.ProjectCost;
import com.example.work.entity.Response;
import com.example.work.service.IProjectCostService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目成本Controller
 *
 * @author horou
 * @date 2022-03-19
 */
@RestController
@RequestMapping("/financial/projectCost")
public class ProjectCostController
{
    @Resource
    private IProjectCostService projectCostService;

    /**
     * 核算项目成本
     */
    @PutMapping("/account/{projectIds}")
    @ResponseBody
    public Response<ProjectCost> account(@PathVariable Long[] projectIds)
    {
        Response<ProjectCost> response=new Response<>();
        if(projectCostService.accountProjectCostByProjectIds(projectIds)){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 获取利润分析数据
     */
//    @PreAuthorize("@ss.hasPermi('financial:projectCost:account')")
//    @GetMapping("/rate")
//    @ResponseBody
//    public Response<ProjectCost> rate()
//    {
//        AjaxResult ajaxResult = projectCostService.selectRate();
//        return ajaxResult;
//    }


    /**
     * 查询项目成本列表
     */
    @GetMapping("/list")
    public Response<ProjectCost> list(Long projectId)
    {
        Response<ProjectCost> response=new Response<>();

        List<ProjectCost> list = projectCostService.selectProjectCostList(projectId);
        response.setList(list);
        response.setMessage("success");
        return response;
    }

    /**
     * 导出项目成本列表
     */
//    public void export(HttpServletResponse response, ProjectCost projectCost)
//    {
//        List<ProjectCost> list = projectCostService.selectProjectCostList(projectCost);
//        ExcelUtil<ProjectCost> util = new ExcelUtil<ProjectCost>(ProjectCost.class);
//        util.exportExcel(response, list, "项目成本数据");
//    }

    /**
     * 获取项目成本详细信息
     */
    @GetMapping(value = "/{projectCostId}")
    public Response<ProjectCost> getInfo(@PathVariable("projectCostId") Long projectCostId)
    {
        Response<ProjectCost> response=new Response<>();
        response.getList().add(projectCostService.selectProjectCostByProjectCostId(projectCostId));
        return response;
    }

    /**
     * 新增项目成本
     */
    @PostMapping
    public Response<ProjectCost> add(@RequestBody ProjectCost projectCost)
    {
//        projectCost.setCreateBy(getUsername());
        Response<ProjectCost> response=new Response<>();
        projectCostService.insertProjectCost(projectCost);
        response.setMessage("success");
        return response;
    }

    /**
     * 修改项目成本
     */
    @PutMapping
    public Response<ProjectCost> edit(@RequestBody ProjectCost projectCost)
    {
        Response<ProjectCost> response=new Response<>();
        projectCostService.updateProjectCost(projectCost);
        response.setMessage("success");
        return response;
    }

    /**
     * 删除项目成本
     */
	@DeleteMapping("/{projectCostIds}")
    public Response<ProjectCost> remove(@PathVariable Long[] projectCostIds)
    {
        Response<ProjectCost> response=new Response<>();
         projectCostService.deleteProjectCostByProjectCostIds(projectCostIds);
        return response;
    }
}
