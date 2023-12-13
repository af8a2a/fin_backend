package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITransactionService extends IService<Transaction> {
    int addTransaction(Transaction transaction);
    List<Transaction> getTransaction(Transaction transaction);
    int deleteTransactionByID(Integer transactionID);
    int editTransaction(Transaction transaction);
    List<Map<String,Object>>  analysis(Transaction transaction);

    List<Map<String, Object>> tax(Date date);
}
