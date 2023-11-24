package com.example.work.service.impl;

import com.example.work.entity.FinInvoice;
import com.example.work.entity.FinReimburse;
import com.example.work.mapper.FinInvoiceMapper;
import com.example.work.mapper.FinReimburseMapper;
import com.example.work.service.IFinReimburseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
@Service
public class FinReimburseServiceImpl implements IFinReimburseService {
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
        return finReimburseMapper.selectFinReimburseByReimburseId(reimburseId);
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
        List<FinReimburse> finReimburseList = finReimburseMapper.selectFinReimburseList(finReimburse);
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
        return finReimburseMapper.insertFinReimburse(finReimburse);
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
        return finReimburseMapper.updateFinReimburse(finReimburse);
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
        return finReimburseMapper.deleteFinReimburseByReimburseIds(reimburseIds);
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
        return finReimburseMapper.deleteFinReimburseByReimburseId(reimburseId);
    }

    /**
     * 上传报销发票信息
     *
     * @param reimburseId 报销主键
     * @param fileName 文件名字
     * @return 结果
     */
    public int addInvoice(Long reimburseId, String fileName)
    {
        String realName = fileName.substring(fileName.lastIndexOf("/") + 1);
        String filePath = null;
        try {
            filePath = fileName.substring(0, fileName.lastIndexOf("/") + 1) + java.net.URLEncoder.encode(realName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //新增发票信息
        FinInvoice finInvoice = new FinInvoice();
        finInvoice.setInvoiceFrom("2");
        finInvoice.setStatus("3");
        finInvoiceMapper.insertFinInvoice(finInvoice);

        //更新发票-文件表

        //更新报销信息
        FinReimburse finReimburse1 = new FinReimburse();
        finReimburse1.setReimburseId(reimburseId);
        finReimburse1.setStatus("2");
        finReimburse1.setInvoiceId(finInvoice.getInvoiceId());
        return finReimburseMapper.updateFinReimburse(finReimburse1);

    }
}