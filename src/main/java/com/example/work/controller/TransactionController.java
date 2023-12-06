package com.example.work.controller;

import com.example.work.entity.FinContract;
import com.example.work.entity.Response;
import com.example.work.entity.Transaction;
import com.example.work.service.ITransactionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial/transaction")

public class TransactionController {
    @Resource
    private ITransactionService finContractService;
    @GetMapping("/list")
    public Response<Transaction> list(Transaction transaction)
    {
        Response response=new Response();
        List<Transaction> list = finContractService.getTransaction(transaction);
        response.setList(list);
        return response;
    }
    @PostMapping("/edit")
    public Response<Transaction> edit(@RequestBody Transaction transaction)
    {
        Response<Transaction> response=new Response<>();
        finContractService.editTransaction(transaction);
        return response;

    }

    @PostMapping("/add")
    public Response<Transaction> add(@RequestBody Transaction transaction)
    {
        Response<Transaction> response=new Response<>();
        finContractService.addTransaction(transaction);
        return response;
    }

}
