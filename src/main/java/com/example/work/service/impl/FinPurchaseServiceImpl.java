package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinInvoice;
import com.example.work.entity.FinPurchase;
import com.example.work.mapper.FinInvoiceMapper;
import com.example.work.mapper.FinPurchaseMapper;
import com.example.work.service.IFinInvoiceService;
import com.example.work.service.IFinPurchaseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service

public class FinPurchaseServiceImpl extends ServiceImpl<FinPurchaseMapper, FinPurchase> implements IFinPurchaseService {
    @Resource
    private FinPurchaseMapper finPurchaseMapper;

    /**
     * 查询采购订单
     *
     * @param purchaseId 采购订单主键
     * @return 采购订单
     */
    @Override
    public FinPurchase selectFinPurchaseByPurchaseId(Long purchaseId)
    {

        return finPurchaseMapper.selectById(purchaseId);
    }

    /**
     * 查询采购订单列表
     *
     * @param finPurchase 采购订单
     * @return 采购订单
     */
    @Override
    public List<FinPurchase> selectFinPurchaseList(FinPurchase finPurchase)
    {
        QueryWrapper<FinPurchase> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(!finPurchase.getPurchaseName().isEmpty(),FinPurchase::getPurchaseName,finPurchase.getPurchaseName())
                .eq(!finPurchase.getPurchaseType().isEmpty(),FinPurchase::getPurchaseType,finPurchase.getPurchaseType())
                .eq(!finPurchase.getPurchaser().isEmpty(),FinPurchase::getPurchaser,finPurchase.getPurchaser())
                .eq(!finPurchase.getSupplier().isEmpty(),FinPurchase::getSupplier,finPurchase.getSupplier())
                .eq(!finPurchase.getStatus().isEmpty(),FinPurchase::getStatus,finPurchase.getStatus());

        List<FinPurchase> finPurchaseList = finPurchaseMapper.selectList(wrapper);
        return finPurchaseList;
    }

    /**
     * 新增采购订单
     *
     * @param finPurchase 采购订单
     * @return 结果
     */
    @Override
    public int insertFinPurchase(FinPurchase finPurchase)
    {
        finPurchase.setCreateTime(new Date());
        save(finPurchase);
        return 1;
    }

    /**
     * 修改采购订单
     *
     * @param finPurchase 采购订单
     * @return 结果
     */
    @Override
    public int updateFinPurchase(FinPurchase finPurchase)
    {
        finPurchase.setUpdateTime(new Date());

        return finPurchaseMapper.updateById(finPurchase);
    }

    /**
     * 批量删除采购订单
     *
     * @param purchaseIds 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    public int deleteFinPurchaseByPurchaseIds(Long[] purchaseIds)
    {
        return finPurchaseMapper.deleteBatchIds(Arrays.stream(purchaseIds).toList());
    }

    /**
     * 删除采购订单信息
     *
     * @param purchaseId 采购订单主键
     * @return 结果
     */
    @Override
    public int deleteFinPurchaseByPurchaseId(Long purchaseId)
    {
        return finPurchaseMapper.deleteById(purchaseId);
    }

    /**
     * 上传采购发票信息
     *
     * @param purchaseId 采购主键
     * @param fileName 文件名字
     * @return 结果
     */
    @Deprecated
    public int addInvoice(Long purchaseId, String fileName){
//        String realName = fileName.substring(fileName.lastIndexOf("/") + 1);
//        String filePath = null;
//        try {
//            filePath = fileName.substring(0, fileName.lastIndexOf("/") + 1) + java.net.URLEncoder.encode(realName, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        FinInvoice finInvoice = new FinInvoice();
//        finInvoice.setInvoiceFrom("3");
//        finInvoice.setStatus("3");
//        finInvoiceMapper.insertFinInvoice(finInvoice);
//
//        //更新发票-文件表
//
//        //更新采购信息
//        FinPurchase finPurchase1 = new FinPurchase();
//        finPurchase1.setPurchaseId(purchaseId);
//        finPurchase1.setStatus("2");
//        finPurchase1.setInvoiceId(finInvoice.getInvoiceId());
//        return finPurchaseMapper.updateFinPurchase(finPurchase1);
        return 0;
    }
}