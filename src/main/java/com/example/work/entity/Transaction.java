package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
//交易记账
public class Transaction {
    @TableId(type = IdType.AUTO)
    Integer transactionId;
    BigDecimal incoming;
    String name;
    Date date;
}
