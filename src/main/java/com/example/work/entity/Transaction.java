package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("transaction")

//交易记账
public class Transaction {
    @TableId(type = IdType.AUTO)
    Integer transactionId;
    BigDecimal incoming;
    String name;
    String type;
    Date date;
    String company;
    String status;

}
