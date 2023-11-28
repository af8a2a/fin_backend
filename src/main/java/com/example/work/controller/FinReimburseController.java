package com.example.work.controller;


import com.example.work.entity.FinReimburse;
import com.example.work.entity.Response;
import com.example.work.service.IFinInvoiceService;
import com.example.work.service.IFinReimburseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 报销Controller
 *
 * @author horou
 * @date 2022-03-20
 */
@RestController
@RequestMapping("/financial/reimburse")
public class FinReimburseController
{
    @Autowired
    private IFinReimburseService finReimburseService;

    @Autowired
    private IFinInvoiceService finInvoiceService;

//    @Autowired
//    private ServerConfig serverConfig;

    /**
     * 查询报销列表
     */
    @GetMapping("/list")
    public Response<FinReimburse> list(FinReimburse finReimburse)
    {
        Response<FinReimburse> response = new Response<>();
        response.setList(finReimburseService.selectFinReimburseList(finReimburse));
        return response;
    }

    /**
     * 导出报销列表
     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, FinReimburse finReimburse)
//    {
//        List<FinReimburse> list = finReimburseService.selectFinReimburseList(finReimburse);
//        ExcelUtil<FinReimburse> util = new ExcelUtil<FinReimburse>(FinReimburse.class);
//        util.exportExcel(response, list, "报销数据");
//    }

    /**
     * 获取报销详细信息
     */
    @GetMapping(value = "/{reimburseId}")
    public Response<FinReimburse> getInfo(@PathVariable("reimburseId") Long reimburseId)
    {
        Response<FinReimburse> response = new Response<>();
        response.getList().add(finReimburseService.selectFinReimburseByReimburseId(reimburseId));
        return response;
    }

    /**
     * 新增报销
     */
    @PostMapping
    public Response<FinReimburse> add(@RequestBody FinReimburse finReimburse)
    {
        Response<FinReimburse> response = new Response<>();

//        finReimburse.setCreateBy(getUsername());
        if(finReimburseService.insertFinReimburse(finReimburse)!=0){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 修改报销
     */
    @PutMapping
    public Response<FinReimburse> edit(@RequestBody FinReimburse finReimburse)
    {
        Response<FinReimburse> response = new Response<>();
        if(finReimburseService.updateFinReimburse(finReimburse)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 删除报销
     */
    @DeleteMapping("/{reimburseIds}")
    public Response<FinReimburse> remove(@PathVariable Long[] reimburseIds)
    {
        Response<FinReimburse> response = new Response<>();
        if(finReimburseService.deleteFinReimburseByReimburseIds(reimburseIds)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 状态修改
     */
    //@PreAuthorize("@ss.hasPermi('financial:reimburse:edit')")
    //@Log(title = "报销管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public Response<FinReimburse> changeStatus(@RequestBody FinReimburse finReimburse)
    {
        Response<FinReimburse> response = new Response<>();
        if(finReimburseService.updateFinReimburse(finReimburse)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 报销发票上传
     */
//    @PostMapping("/upload")
//    public Response<FinReimburse> uploadInvoice(MultipartFile file, @Param("reimburseId") Long reimburseId) throws Exception
//    {
//        try
//        {
//            FinReimburse finReimburse = finReimburseService.selectFinReimburseByReimburseId(reimburseId);
//            if(finReimburse != null ){
//                if(Integer.parseInt(finReimburse.getStatus()) >= 2 ){
//                    return AjaxResult.error("报销单号:("+finReimburse.getReimburseNumber()+")所在状态不能上传发票");
//                }
//            }
//            // 上传文件路径
//            String filePath = RuoYiConfig.getInvoicePath();
//            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
//            //添加发票文件信息到数据库
//            finReimburseService.addInvoice(reimburseId, fileName);
//            AjaxResult ajax = AjaxResult.success();
//            ajax.put("fileName", fileName);
//            ajax.put("newFileName", FileUtils.getName(fileName));
//            ajax.put("originalFilename", file.getOriginalFilename());
//            return ajax;
//        }
//        catch (Exception e)
//        {
//            return AjaxResult.error(e.getMessage());
//        }
//    }
}
