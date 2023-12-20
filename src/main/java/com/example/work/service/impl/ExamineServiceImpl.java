package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Examine;
import com.example.work.entity.ExamineDTO;
import com.example.work.entity.FinWages;
import com.example.work.entity.Transaction;
import com.example.work.mapper.ExamineMapper;
import com.example.work.mapper.FinWagesMapper;
import com.example.work.mapper.TransactionMapper;
import com.example.work.service.IExamineService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        wrapper.lambda().eq(Examine::getExamineCompany,examine.getExamineCompany());
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectCompany(Examine examine) {
        QueryWrapper<Examine> wrapper=new QueryWrapper<>();
        wrapper.select("examine_company").groupBy("examine_company");
        return mapper.selectMaps(wrapper);
    }

    @Override
    public int finishExamine(ExamineDTO examineDTO) {
        Examine examine=mapper.selectById(examineDTO.getExamineId());
        if(Objects.equals(examine.getType(), "营业审核")){
            if("1".equals(examineDTO.getPass())) {
                UpdateWrapper<Transaction> wrapper = new UpdateWrapper<>();
                wrapper.eq("transaction_id", examine.getCommitId())
                        .set("status", "已审核");
                mapper.deleteById(examine.getExamineId());
                return transactionMapper.update(new Transaction(), wrapper);
            }else{
                mapper.deleteById(examine.getExamineId());
                transactionMapper.deleteById(examine.getExamineId());
            }
        }else if(Objects.equals(examine.getType(), "工资审核")) {

            if ("1".equals(examineDTO.getPass())) {
                UpdateWrapper<FinWages> wrapper = new UpdateWrapper<>();
                wrapper.eq("wage_id", examine.getCommitId())
                        .set("status", "已审核");
                mapper.deleteById(examine.getExamineId());
                return finWagesMapper.update(new FinWages(), wrapper);
            } else {
                mapper.deleteById(examine.getExamineId());
                finWagesMapper.deleteById(examine.getExamineId());
            }
        }
            return 0;

    }

    @Override
    public int addExamine(Examine examine) {
        examine.setExamineTime(new Date());
        save(examine);
        return 1;
    }
}
