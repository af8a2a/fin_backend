package com.example.work.controller;

import com.example.work.entity.FinContract;
import com.example.work.entity.FinInvoice;
import com.example.work.entity.Response;
import com.example.work.service.IFinInvoiceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 发票Controller
 * 
 * @author horou
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/financial/invoice")
public class FinInvoiceController
{

    @Autowired
    private IFinInvoiceService finInvoiceService;


    /**
     * 查询发票列表
     */
    @GetMapping("/list")
    public Response<FinInvoice> list(FinInvoice finInvoice)
    {
        Response<FinInvoice> response=new Response<>();
        response.setList(finInvoiceService.selectFinInvoiceList(finInvoice));
        return response;
    }

//    /**
//     * 导出发票列表
//     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, FinInvoice finInvoice)
//    {
//        List<FinInvoice> list = finInvoiceService.selectFinInvoiceList(finInvoice);
//        ExcelUtil<FinInvoice> util = new ExcelUtil<FinInvoice>(FinInvoice.class);
//        util.exportExcel(response, list, "发票数据");
//    }

    /**
     * 获取发票详细信息
     */
    @GetMapping(value = "/{invoiceId}")
    public Response<FinInvoice> getInfo(@PathVariable("invoiceId") Long invoiceId)
    {
        Response<FinInvoice> response=new Response<>();
        response.getList().add(finInvoiceService.selectFinInvoiceByInvoiceId(invoiceId));
        return response;
    }

    /**
     * 新增发票
     */
    @PostMapping
    public Response<FinInvoice> add(@RequestBody FinInvoice finInvoice)
    {
        Response<FinInvoice> response=new Response<>();
        if(finInvoiceService.insertFinInvoice(finInvoice)!=0){
            response.setMessage("success");
        }
        return response;
    }

    /**
     * 修改发票
     */
    @PutMapping
    public Response<FinInvoice> edit(@RequestBody FinInvoice finInvoice)
    {
        Response<FinInvoice> response=new Response<>();
        if(finInvoiceService.updateFinInvoice(finInvoice)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 删除发票
     */
	@DeleteMapping("/{invoiceIds}")
    public Response<FinInvoice> remove(@PathVariable Long[] invoiceIds)
    {
        Response<FinInvoice> response=new Response<>();
        if(finInvoiceService.deleteFinInvoiceByInvoiceIds(invoiceIds)!=0){
            response.setMessage("success");
        }
        return response;

    }

    /**
     * 发票图片上传请求
     */
//    @PostMapping("/upload")
//    public AjaxResult uploadInvoice(MultipartFile file, @Param("invoiceId") Long invoiceId) throws Exception
//    {
//        try
//        {
//            FinInvoice finInvoice = finInvoiceService.selectFinInvoiceByInvoiceId(invoiceId);
//            if(finInvoice != null ){
//                if(Integer.parseInt(finInvoice.getStatus()) > 2 ){
//                    return AjaxResult.error("发票ID:("+finInvoice.getInvoiceCode()+")所在状态不能上传发票");
//                }
//            }
//            // 上传文件路径
//            String filePath = RuoYiConfig.getInvoicePath();
//            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
//            //添加发票文件信息到数据库
//            finInvoiceService.addInvoice(finInvoice, fileName);
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
