package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinInvoice;
import com.example.work.entity.FinPurchase;
import com.example.work.entity.FinReimburse;
import com.example.work.mapper.FinInvoiceMapper;
import com.example.work.mapper.FinPurchaseMapper;
import com.example.work.mapper.FinReimburseMapper;
import com.example.work.service.IFinPurchaseService;
import com.example.work.service.IFinReimburseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class FinReimburseServiceImpl extends ServiceImpl<FinReimburseMapper, FinReimburse> implements IFinReimburseService {
    @Resource
    private FinReimburseMapper finReimburseMapper;
    @Resource
    private FinInvoiceMapper finInvoiceMapper;

    /**
     * 查询报销
     *
     * @param reimburseId 报销主键
     * @return 报销
     */
    @Override
    public FinReimburse selectFinReimburseByReimburseId(Long reimburseId)
    {
        return finReimburseMapper.selectById(reimburseId);
    }

    /**
     * 查询报销列表
     *
     * @param finReimburse 报销
     * @return 报销
     */
    @Override
    public List<FinReimburse> selectFinReimburseList(FinReimburse finReimburse)
    {
        QueryWrapper<FinReimburse> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(!finReimburse.getReimburseNumber().isEmpty(),FinReimburse::getReimburseNumber,finReimburse.getReimburseNumber())
                .eq(!finReimburse.getReimburseType().isEmpty(),FinReimburse::getReimburseType,finReimburse.getReimburseType())
                .eq(!finReimburse.getStatus().isEmpty(),FinReimburse::getStatus,finReimburse.getStatus());
        List<FinReimburse> finReimburseList = finReimburseMapper.selectList(wrapper);
        return finReimburseList;
    }

    /**
     * 新增报销
     *
     * @param finReimburse 报销
     * @return 结果
     */
    @Override
    public int insertFinReimburse(FinReimburse finReimburse)
    {
        finReimburse.setCreateTime(new Date());
        return finReimburseMapper.insert(finReimburse);
    }

    /**
     * 修改报销
     *
     * @param finReimburse 报销
     * @return 结果
     */
    @Override
    public int updateFinReimburse(FinReimburse finReimburse)
    {
        finReimburse.setUpdateTime(new Date());
        return finReimburseMapper.updateById(finReimburse);
    }

    /**
     * 批量删除报销
     *
     * @param reimburseIds 需要删除的报销主键
     * @return 结果
     */
    @Override
    public int deleteFinReimburseByReimburseIds(Long[] reimburseIds)
    {
        return finReimburseMapper.deleteBatchIds(Arrays.stream(reimburseIds).toList());
    }

    /**
     * 删除报销信息
     *
     * @param reimburseId 报销主键
     * @return 结果
     */
    @Override
    public int deleteFinReimburseByReimburseId(Long reimburseId)
    {
        return finReimburseMapper.deleteById(reimburseId);
    }

    /**
     * 上传报销发票信息
     *
     * @param reimburseId 报销主键
     * @param fileName 文件名字
     * @return 结果
     */
    @Deprecated
    public int addInvoice(Long reimburseId, String fileName)
    {
        return 0;
    }
}