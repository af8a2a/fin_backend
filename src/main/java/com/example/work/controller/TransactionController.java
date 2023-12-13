package com.example.work.controller;

import com.example.work.entity.Response;
import com.example.work.entity.Transaction;
import com.example.work.service.ITransactionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/financial/transaction")

public class TransactionController {
    @Resource
    private ITransactionService service;

    @PostMapping("/list")
    public Response<Transaction> list(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response();
        List<Transaction> list = service.getTransaction(transaction);
        response.setList(list);
        return response;
    }

    @PostMapping("/update")
    public Response<Transaction> edit(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        if(service.editTransaction(transaction)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;

    }

    @PostMapping("/delete")
    public Response<Transaction> delete(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        if(service.deleteTransactionByID(transaction.getTransactionId())!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;

    }


    @PostMapping("/add")
    public Response<Transaction> add(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        if(service.addTransaction(transaction)!=0){
            response.setMessage("success");
        }else{
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping("/data")
    public Response<Map<String, Object>> fetch(@RequestBody Transaction transaction) {
        Response<Map<String, Object>> response = new Response<>();
        response.setList(service.analysis(transaction));
        return response;
    }
    @PostMapping("/sum")
    public Response<Map<String, Object>> get(@RequestBody Transaction transaction){
        Response<Map<String, Object>> response = new Response<>();
        response.setList(service.tax(transaction.getDate()));
        return response;

    }
}
