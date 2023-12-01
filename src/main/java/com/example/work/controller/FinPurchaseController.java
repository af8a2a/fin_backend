package com.example.work.controller;

import com.example.work.entity.FinPurchase;
import com.example.work.entity.Response;
import com.example.work.service.IFinPurchaseService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 采购订单Controller
 * 
 * @author horou
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/financial/purchase")
public class FinPurchaseController
{
    @Resource
    private IFinPurchaseService finPurchaseService;

    /**
     * 查询采购订单列表
     */
    @GetMapping("/list")
    public Response<FinPurchase> list(FinPurchase finPurchase)
    {
        Response<FinPurchase> response=new Response<>();
        response.setList(finPurchaseService.selectFinPurchaseList(finPurchase));
        return response;
    }

    /**
     * 导出采购订单列表
     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, FinPurchase finPurchase)
//    {
//        List<FinPurchase> list = finPurchaseService.selectFinPurchaseList(finPurchase);
//        ExcelUtil<FinPurchase> util = new ExcelUtil<FinPurchase>(FinPurchase.class);
//        util.exportExcel(response, list, "采购订单数据");
//    }

    /**
     * 获取采购订单详细信息
     */
    @GetMapping(value = "/{purchaseId}")
    public Response<FinPurchase> getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        Response<FinPurchase> response=new Response<>();
        response.getList().add(finPurchaseService.selectFinPurchaseByPurchaseId(purchaseId));
        return response;
    }

    /**
     * 新增采购订单
     */
    @PostMapping
    public Response<FinPurchase> add(@RequestBody FinPurchase finPurchase)
    {
        Response<FinPurchase> response=new Response<>();
        if(finPurchaseService.insertFinPurchase(finPurchase)!=0){
            response.setMessage("success");
        }
        return  response;
    }

    /**
     * 修改采购订单
     */
    @PutMapping
    public Response<FinPurchase> edit(@RequestBody FinPurchase finPurchase)
    {
        Response<FinPurchase> response=new Response<>();
        if(finPurchaseService.updateFinPurchase(finPurchase)!=0){
            response.setMessage("success");
        }
        return  response;
    }

    /**
     * 删除采购订单
     */
	@DeleteMapping("/{purchaseIds}")
    public Response<FinPurchase> remove(@PathVariable Long[] purchaseIds)
    {
        Response<FinPurchase> response=new Response<>();
        if(finPurchaseService.deleteFinPurchaseByPurchaseIds(purchaseIds)!=0){
            response.setMessage("success");
        }
        return  response;

    }

    /**
     * 状态修改
     */
    //@PreAuthorize("@ss.hasPermi('financial:purchase:edit')")
    //@Log(title = "采购管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public Response<FinPurchase> changeStatus(@RequestBody FinPurchase finPurchase)
    {
        Response<FinPurchase> response=new Response<>();
        if(finPurchaseService.updateFinPurchase(finPurchase)!=0){
            response.setMessage("success");
        }
        return  response;
    }

    /**
     * 采购发票上传请求
     */
//    @PostMapping("/upload")
//    public AjaxResult uploadInvoice(MultipartFile file, @Param("purchaseId") Long purchaseId) throws Exception
//    {
//        try
//        {
//            FinPurchase finPurchase = finPurchaseService.selectFinPurchaseByPurchaseId(purchaseId);
//            if(finPurchase != null ){
//                if(Integer.parseInt(finPurchase.getStatus()) >= 2 ){
//                    return AjaxResult.error("采购ID:("+finPurchase.getPurchaseId()+")所在状态不能上传发票");
//                }
//            }
//            // 上传文件路径
//            String filePath = RuoYiConfig.getInvoicePath();
//            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
//            //添加发票文件信息到数据库
//            finPurchaseService.addInvoice(purchaseId, fileName);
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
