package com.example.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.work.entity.Transaction;
import com.example.work.mapper.TransactionMapper;
import com.example.work.service.ITransactionService;
import jakarta.annotation.Resource;

import java.util.Calendar;
import java.util.List;

public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {
    @Resource
    private TransactionMapper mapper;
    @Override
    public int addTransaction(Transaction transaction) {
        save(transaction);
        return 1;
    }

    @Override
    public List<Transaction> getTransaction(Transaction transaction) {
        QueryWrapper<Transaction> wrapper=new QueryWrapper<>();

        if(transaction.getDate()!=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transaction.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // 月份从0开始，需要加1

            wrapper.apply("YEAR(date) = {0}", year)
                    .apply("MONTH(date) = {0}", month);

        }
        return mapper.selectList(wrapper);
    }

    @Override
    public int editTransaction(Transaction transaction) {
        updateById(transaction);
        return 1;
    }
}
