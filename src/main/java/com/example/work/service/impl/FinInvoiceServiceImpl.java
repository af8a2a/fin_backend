package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.FinInvoice;
import com.example.work.mapper.FinContractMapper;
import com.example.work.mapper.FinInvoiceMapper;
import com.example.work.service.IFinInvoiceService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class FinInvoiceServiceImpl extends ServiceImpl<FinInvoiceMapper, FinInvoice> implements IFinInvoiceService{

    @Resource
    private FinInvoiceMapper mapper;
    /**
     * 查询发票
     *
     * @param invoiceId 发票主键
     * @return 发票
     */
    @Override
    public FinInvoice selectFinInvoiceByInvoiceId(Long invoiceId) {
        return mapper.selectById(invoiceId);
    }

    /**
     * 查询发票列表
     *
     * @param finInvoice 发票
     * @return 发票集合
     */
    @Override
    public List<FinInvoice> selectFinInvoiceList(FinInvoice finInvoice) {
        QueryWrapper<FinInvoice> wrapper=new QueryWrapper<>();
        wrapper.lambda()
                .eq(!finInvoice.getInvoiceType().isEmpty(),FinInvoice::getInvoiceType,finInvoice.getInvoiceType())
                .eq(!finInvoice.getInvoiceFrom().isEmpty(),FinInvoice::getInvoiceFrom,finInvoice.getInvoiceFrom())
                .eq(!finInvoice.getInvoiceName().isEmpty(),FinInvoice::getInvoiceName,finInvoice.getInvoiceName())
                .eq(!finInvoice.getInvoiceCode().isEmpty(),FinInvoice::getInvoiceCode,finInvoice.getInvoiceCode())
                .eq(!finInvoice.getInvoiceNumber().isEmpty(),FinInvoice::getInvoiceNumber,finInvoice.getInvoiceNumber());
        return mapper.selectList(wrapper);
    }

    /**
     * 新增发票
     *
     * @param finInvoice 发票
     * @return 结果
     */
    @Override
    public int insertFinInvoice(FinInvoice finInvoice) {
        save(finInvoice);
        return 1;
    }

    /**
     * 修改发票
     *
     * @param finInvoice 发票
     * @return 结果
     */
    @Override
    public int updateFinInvoice(FinInvoice finInvoice) {
        updateById(finInvoice);
        return 1;
    }

    /**
     * 批量删除发票
     *
     * @param invoiceIds 需要删除的发票主键集合
     * @return 结果
     */
    @Override
    public int deleteFinInvoiceByInvoiceIds(Long[] invoiceIds) {

        return mapper.deleteBatchIds(Arrays.stream(invoiceIds).toList());
    }

    /**
     * 删除发票信息
     *
     * @param invoiceId 发票主键
     * @return 结果
     */
    @Override
    public int deleteFinInvoiceByInvoiceId(Long invoiceId) {
        return mapper.deleteById(invoiceId);
    }

    /**
     * 添加发票文件
     *
     * @param invoiceId 发票主键
     * @param fileId    文件主键
     * @retyrn
     */
    @Override
    public int insertFinInvoiceFile(Long invoiceId, Long fileId) {
        return 0;
    }

    /**
     * 上传采购发票信息
     *
     * @param finInvoice 发票信息
     * @param fileName   文件名字
     * @return 结果
     */
    @Override
    public int addInvoice(FinInvoice finInvoice, String fileName) {
        return 0;
    }
}



