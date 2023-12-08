package com.example.work.controller;

import com.example.work.entity.FinWages;
import com.example.work.entity.Response;
import com.example.work.service.IFinWagesService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工资Controller
 *
 * @author horou
 * @date 2022-03-18
 */
@RestController
@CrossOrigin
@RequestMapping("/financial/wages")
public class FinWagesController
{
    @Resource
    private IFinWagesService finWagesService;

    /**
     * 查询工资列表
     */
    @PostMapping("/list")

    public Response<FinWages> list(@RequestBody FinWages finWages)
    {
        Response<FinWages> response=new Response<>();
        response.setList(finWagesService.selectFinWagesList(finWages));
        return response;
    }



    /**
     * 获取工资详细信息
     */
    @GetMapping(value = "/{wageId}")
    public Response<FinWages> getInfo(@PathVariable("wageId") Long wageId)
    {
        Response<FinWages> response=new Response<>();
        response.getList().add(finWagesService.selectFinWagesByWageId(wageId));
        return response;

    }

    /**
     * 新增工资
     */
    @PostMapping("/add")
    public Response<FinWages> add(@RequestBody FinWages finWages)
    {
        Response<FinWages> response=new Response<>();

        if(finWagesService.insertFinWages(finWages)!=0){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 修改工资
     */
    @PutMapping
    public Response<FinWages> edit(@RequestBody FinWages finWages)
    {
        Response<FinWages> response=new Response<>();

        if(finWagesService.updateFinWages(finWages)!=0){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 删除工资
     */
	@DeleteMapping("/{wageIds}")
    public Response<FinWages> remove(@PathVariable Long[] wageIds)
    {
        Response<FinWages> response=new Response<>();

        if(finWagesService.deleteFinWagesByWageIds(wageIds)!=0){
            response.setMessage("success");
        }
        return response;

    }
}
