package com.example.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发票对象 fin_invoice
 *
 */
@Data
public class FinInvoice extends BaseEntity{
    /** 发票ID */
    private Long invoiceId;

    /** 项目ID */
    private Long projectId;

    /** 发票类型 */
    private String invoiceType;

    /** 发票来源" */
    private String invoiceFrom;

    /** 发票名称 */
    private String invoiceName;

    /** 发票代码 */
    private String invoiceCode;

    /** 发票号码 */
    private String invoiceNumber;

    /** 开票日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    /** 购方名称 */
    private String purchasesName;

    /** 购方纳税人识别号 */
    private String purchasesNumber;

    /** 购方地址及电话 */
    private String purchasesAddressTel;

    /** 购方开户行及账号 */
    private String purchasesBankersAccount;

    /** 销售方名称 */
    private String salersName;

    /** 销售方纳税人识别号 */
    private String salersNumber;

    /** 销售方地址及电话 */
    private String salersAddressTel;

    /** 销售方开户行及账号 */
    private String salersBankersAccount;

    /** 合计金额 */
    private BigDecimal total;

    /** 合计税额 */
    private BigDecimal tax;

    /** 价税合计(大写) */
    private String pricePlusChinese;

    /** 价税合计(小写) */
    private BigDecimal pricePlusArabic;

    /** 密码区 */
    private String password;

    /** 收款人 */
    private String payee;

    /** 复核 */
    private String review;

    /** 开票人 */
    private String drawer;

    /** 备注 */
    private String memo;

    /** 状态 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;



}
