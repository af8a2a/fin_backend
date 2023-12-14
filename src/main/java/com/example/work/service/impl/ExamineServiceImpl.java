package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Examine;
import com.example.work.entity.FinWages;
import com.example.work.entity.Transaction;
import com.example.work.mapper.ExamineMapper;
import com.example.work.mapper.FinWagesMapper;
import com.example.work.mapper.TransactionMapper;
import com.example.work.service.IExamineService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements IExamineService {
    @Resource
    private ExamineMapper mapper;
    @Resource
    private FinWagesMapper finWagesMapper;

    @Resource
    private TransactionMapper transactionMapper;
    @Override
    public List<Examine> selectExamineList(Examine examine) {
        QueryWrapper<Examine> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(Examine::getExamineCompany,examine.getExamineCompany())
                .eq(Examine::getStatus,"待审核");
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectCompany(Examine examine) {
        QueryWrapper<Examine> wrapper=new QueryWrapper<>();
        wrapper.select("examine_company");
        return mapper.selectMaps(wrapper);
    }

    @Override
    public int finishExamine(Examine examine) {
        if(Objects.equals(examine.getType(), "营业审核")){
            UpdateWrapper<Transaction> wrapper=new UpdateWrapper<>();
            wrapper.eq("transaction_id",examine.getCommitId())
                    .set("status","审核通过");;
            Map<String, Object> updateFields = new HashMap<>();
            updateFields.put("status","审核通过");


            return transactionMapper.update(new Transaction(),wrapper);
        }else if(Objects.equals(examine.getType(), "工资审核")){
            UpdateWrapper<FinWages> wrapper=new UpdateWrapper<>();
            wrapper.eq("wage_id",examine.getCommitId())
                    .set("status","审核通过");
            Map<String, Object> updateFields = new HashMap<>();
            updateFields.put("status","审核通过");
            return finWagesMapper.update(new FinWages(),wrapper);
        }else{
            return 0;
        }

    }

    @Override
    public int addExamine(Examine examine) {
        examine.setExamineTime(new Date());
        examine.setStatus("待审核");
        save(examine);
        return 1;
    }
}
