package com.example.work.controller;


import com.example.work.entity.FinContract;
import com.example.work.entity.Response;
import com.example.work.service.IFinContractService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 合同管理Controller
 *
 * @author horou
 * @date 2022-03-08
 */
@RestController
@RequestMapping("/financial/contract")
public class FinContractController
{
    @Resource
    private IFinContractService finContractService;

    /**
     * 查询合同管理列表
     */
    @GetMapping("/list")
    public Response<FinContract> list(FinContract finContract)
    {
        Response response=new Response();
        List<FinContract> list = finContractService.selectFinContractList(finContract);
        response.setList(list);
        return response;
    }

//    /**
//     * 导出合同管理列表
//     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, FinContract finContract)
//    {
//        List<FinContract> list = finContractService.selectFinContractList(finContract);
//        ExcelUtil<FinContract> util = new ExcelUtil<FinContract>(FinContract.class);
//        util.exportExcel(response, list, "合同管理数据");
//    }

//    @Log(title = "合同管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('financial:contract:import')")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
//    {
//        ExcelUtil<FinContract> util = new ExcelUtil<FinContract>(FinContract.class);
//        List<FinContract> finContractList = util.importExcel(file.getInputStream());
//        String operName = getUsername();
//        String message = finContractService.importFinContract(finContractList, updateSupport, operName);
//        return AjaxResult.success(message);
//    }

//    @PostMapping("/importTemplate")
//    public void importTemplate(HttpServletResponse response)
//    {
//        ExcelUtil<FinContract> util = new ExcelUtil<FinContract>(FinContract.class);
//        util.importTemplateExcel(response, "合同数据");
//    }

    /**
     * 获取合同管理详细信息
     */
    @GetMapping(value = "/{contractId}")
    public Response<FinContract> getInfo(@PathVariable("contractId") Long contractId)
    {
        Response<FinContract> response=new Response<>();
        response.getList().add(finContractService.selectFinContractByContractId(contractId));
        return response;
    }

    /**
     * 新增合同管理
     */
    @PostMapping
    public Response<FinContract> add(@RequestBody FinContract finContract)
    {
        Response<FinContract> response=new Response<>();

        if (!finContractService.checkFnContractNumberUnique(finContract).isEmpty())
        {
            response.setMessage("新增角色'" + finContract.getContractNumber() + "'失败，合同编号已存在");
            return response;
        }
        if(finContractService.insertFinContract(finContract)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 修改合同管理
     */
    @PutMapping
    public Response<FinContract> edit(@RequestBody FinContract finContract)
    {
        Response<FinContract> response=new Response<>();

        if (!finContractService.checkFnContractNumberUnique(finContract).isEmpty())
        {
            response.setMessage("合同编号不存在");
            return response;
        }
        if(finContractService.updateFinContract(finContract)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 删除合同管理
     */
    @DeleteMapping("/{contractIds}")
    public Response<FinContract> remove(@PathVariable Long[] contractIds)
    {
        Response<FinContract> response=new Response<>();

        if(finContractService.deleteFinContractByContractIds(contractIds)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;

    }

    /**
     * 状态修改
     */
    //@PreAuthorize("@ss.hasPermi('financial:contract:edit')")
    //@Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public Response<FinContract> changeStatus(@RequestBody FinContract finContract)
    {
        System.out.println("changeStatus changeStatus");
        System.out.println(finContract);
        //finContract.setStatus("3");
        Response<FinContract> response=new Response<>();
        if(finContractService.updateFinContract(finContract)!=0){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 合同文件上传
     */
//    @Log(title = "合同文件上传", businessType = BusinessType.UPLOAD)
//    @PostMapping("/upload")
//    public AjaxResult uploadInvoice(MultipartFile file, Long contractId) throws Exception
//    {
//        try
//        {
//            System.out.println("contractId:" + contractId);
//            FinContract finContract = finContractService.selectFinContractByContractId(contractId);
//            if(finContract != null ){
//                if(Integer.parseInt(finContract.getStatus()) >= 99 ){//暂时关闭合同上传限制
//                    return AjaxResult.error("合同:("+finContract.getContractName()+")所在状态不能上传合同文件");
//                }
//            }
//            // 上传文件路径
//            String filePath = RuoYiConfig.getContractPath();
//            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
//            //添加合同文件信息到数据库
//            int a = finContractService.addContractFile(contractId, fileName);
//
//            AjaxResult ajax;
//            if(a>0){
//                ajax = AjaxResult.success();
//            }else {
//                ajax = AjaxResult.error();
//            }
//
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
