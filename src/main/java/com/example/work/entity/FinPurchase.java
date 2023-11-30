package com.example.work.entity;

import lombok.Data;

import java.util.Date;

/**
 * 采购订单对象 fin_purchase
 *
 */
@Data
public class FinPurchase extends BaseEntity{
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
