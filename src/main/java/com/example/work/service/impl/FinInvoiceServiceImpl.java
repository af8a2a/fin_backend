package com.example.work.service.impl;

import com.example.work.entity.FinInvoice;
import com.example.work.mapper.FinContractMapper;
import com.example.work.mapper.FinInvoiceMapper;
import com.example.work.service.IFinInvoiceService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
@Service
public class FinInvoiceServiceImpl implements IFinInvoiceService{
    @Resource
    private FinInvoiceMapper finInvoiceMapper;
    @Resource
    private FinContractMapper finContractMapper;


    /**
     * 查询发票
     *
     * @param invoiceId 发票主键
     * @return 发票
     */
    @Override
    public FinInvoice selectFinInvoiceByInvoiceId(Long invoiceId)
    {
        return finInvoiceMapper.selectFinInvoiceByInvoiceId(invoiceId);
    }

    /**
     * 查询发票列表
     *
     * @param finInvoice 发票
     * @return 发票
     */
    @Override
    public List<FinInvoice> selectFinInvoiceList(FinInvoice finInvoice)
    {
        List<FinInvoice> finInvoiceList = finInvoiceMapper.selectFinInvoiceList(finInvoice);
        for(int i = 0; i < finInvoiceList.size(); i++){
            FinInvoice finInvoice1 = finInvoiceList.get(i);
//            SysFileInfo sysFileInfo = finInvoice1.getSysFileInfo();
//            if(sysFileInfo != null){
//                SysFileInfo finalSysFileInfo = sysFileInfo;
//                String filePath = OptionalUtil.or(() ->serverConfig.getUrl() + finalSysFileInfo.getFilePath(), "");
//                sysFileInfo.setFilePath(filePath);
//            }else {
//                sysFileInfo = new SysFileInfo();
//                sysFileInfo.setFilePath("");
//            }
//            finInvoice1.setSysFileInfo(sysFileInfo);
            finInvoiceList.set(i,finInvoice1);
        }
        return finInvoiceList;
    }

//    /**
//     * 查询同一来源所有发票信息
//     *
//     * @param invoiceFrom 发票来源
//     * @return 发票
//     */
//    @Override
//    public List<FinInvoice> selectFinInvoiceByInvoiceFrom(String invoiceFrom)
//    {
//        return finInvoiceMapper.selectFinInvoiceByInvoiceFrom(invoiceFrom);
//    }

    /**
     * 新增发票
     *
     * @param finInvoice 发票
     * @return 结果
     */
    @Override
    public int insertFinInvoice(FinInvoice finInvoice)
    {
        finInvoice.setCreateTime(new Date());
        return finInvoiceMapper.insertFinInvoice(finInvoice);
    }

    /**
     * 修改发票
     *
     * @param finInvoice 发票
     * @return 结果
     */
    @Override
    public int updateFinInvoice(FinInvoice finInvoice)
    {

        FinInvoice finInvoice1 = finInvoiceMapper.selectFinInvoiceByInvoiceId(finInvoice.getInvoiceId());
        if(finInvoice.getStatus().equals("3") && finInvoice1.getInvoiceFrom().equals("4")){
            finContractMapper.updateFinContractReceivable(finInvoice1.getProjectId(),finInvoice1.getTotal());
        }
        return finInvoiceMapper.updateFinInvoice(finInvoice);
    }

    /**
     * 批量删除发票
     *
     * @param invoiceIds 需要删除的发票主键
     * @return 结果
     */
    @Override
    public int deleteFinInvoiceByInvoiceIds(Long[] invoiceIds)
    {
        return finInvoiceMapper.deleteFinInvoiceByInvoiceIds(invoiceIds);
    }

    /**
     * 删除发票信息
     *
     * @param invoiceId 发票主键
     * @return 结果
     */
    @Override
    public int deleteFinInvoiceByInvoiceId(Long invoiceId)
    {
        return finInvoiceMapper.deleteFinInvoiceByInvoiceId(invoiceId);
    }

    /**
     * 添加发票文件
     *
     * @param invoiceId 发票主键
     * @param fileId 文件主键
     * @retyrn
     */
    @Override
    public int insertFinInvoiceFile(Long invoiceId, Long fileId)
    {
        return finInvoiceMapper.insertFinInvoiceFile(invoiceId, fileId);

    }

    /**
     * 上传采购发票信息
     *
     * @param finInvoice 发票信息
     * @param fileName 文件名字
     * @return 结果
     */
    @Override
    public int addInvoice(FinInvoice finInvoice, String fileName){
        String realName = fileName.substring(fileName.lastIndexOf("/") + 1);
        String filePath = null;
        try {
            filePath = fileName.substring(0, fileName.lastIndexOf("/") + 1) + java.net.URLEncoder.encode(realName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //文件更新到sys_file_info

        //更新发票-文件表

        //更新发票信息
        finInvoice.setStatus("2");
        return finInvoiceMapper.updateFinInvoice(finInvoice);
    }

}



