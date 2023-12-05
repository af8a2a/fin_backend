package com.example.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.work.entity.Transaction;

import java.util.List;

public interface ITransactionService extends IService<Transaction> {
    int addTransaction(Transaction transaction);
    List<Transaction> getTransaction(Transaction transaction);

    int editTransaction(Transaction transaction);
}
