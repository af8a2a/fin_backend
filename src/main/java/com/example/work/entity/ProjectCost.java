package com.example.work.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目成本对象 pro_project_cost
 *
 */
@Data
public class ProjectCost {
    private Long projectCostId;

    /** 项目ID */
    private Long projectId;

    /** 营业税金 */
    private BigDecimal businessTax;

    /** 管理成本 */
    private BigDecimal managenmentCost;

    /** 人员成本 */
    private BigDecimal personnelCost;

    /** 采购支出 */
    private BigDecimal procurementCost;

    /** 其他支出 */
    private BigDecimal othersCost;

    /** 维护成本 */
    private BigDecimal maintenanceCost;

    /** 帐期总收入 */
    private BigDecimal generalIncome;

    /** 税前利润 */
    private BigDecimal preTax;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private ProProject project;

    private FinContract contract;

}
