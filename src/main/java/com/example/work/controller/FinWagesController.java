package com.example.work.controller;

import com.example.work.entity.FinWages;
import com.example.work.entity.Response;
import com.example.work.service.IFinWagesService;
import io.swagger.models.auth.In;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
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
	@PostMapping("/delete")
    public Response<FinWages> remove(@RequestBody FinWages finWage)
    {
        Response<FinWages> response=new Response<>();

        if(finWagesService.deleteFinWagesByWageId(finWage.getWageId())!=0){
            response.setMessage("success");
        }
        return response;

    }
}
