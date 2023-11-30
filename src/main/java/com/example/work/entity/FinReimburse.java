package com.example.work.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinReimburse extends BaseEntity{
    /** 主键ID */
    private Long reimburseId;

    /** 报销单号 */
    private String reimburseNumber;

    /** 报销部门 */
    private Long deptId;

    /** 项目编号 */
    private Long projectId;

    /** 发票ID */
    private Long invoiceId;

    /** 报销金额 */
    private BigDecimal amount;

    /** 报销类型 */
    private String reimburseType;

    /** 费用说明 */
    private String details;

    /** 报销状态 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

}
