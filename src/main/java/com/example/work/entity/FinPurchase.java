package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 采购订单对象 fin_purchase
 *
 */
@Data
@TableName("purchase")
public class FinPurchase extends BaseEntity{
    @TableId(type = IdType.AUTO)

    Long purchaseId;
    Long projectId;
    Long invoiceId;
    String purchaseName;
    String purchaseType;
    String purchaser;
    String supplier;
    String status;
    Date purchaseDate;
    Boolean delFlag;
}
