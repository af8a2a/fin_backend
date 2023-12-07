package com.example.work.controller;

import com.example.work.entity.FinContract;
import com.example.work.entity.Response;
import com.example.work.entity.Transaction;
import com.example.work.service.ITransactionService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/financial/transaction")

public class TransactionController {
    @Resource
    private ITransactionService service;

    @GetMapping("/list")
    public Response<Transaction> list(Transaction transaction) {
        Response response = new Response();
        List<Transaction> list = service.getTransaction(transaction);
        response.setList(list);
        return response;
    }

    @PostMapping("/edit")
    public Response<Transaction> edit(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        service.editTransaction(transaction);
        return response;

    }

    @PostMapping("/add")
    public Response<Transaction> add(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        service.addTransaction(transaction);
        return response;
    }

    @GetMapping("/data")
    public Response<Map<String, Object>> fetch() {
        Response<Map<String, Object>> response = new Response<>();
        response.setList(service.analysis());
        return response;
    }
    @PostMapping("/sum")
    public Response<Map<String, Object>> get(@RequestBody Transaction transaction){
        Response<Map<String, Object>> response = new Response<>();
        response.setList(service.tax(transaction.getDate()));
        return response;

    }
}
