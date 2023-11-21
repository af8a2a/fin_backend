package com.example.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinContract extends BaseEntity{
    /** 合同编号 */
    private Long contractId;

    /** 合同名称 */
    private String contractName;

    /** 合同编号 */
    private String contractNumber;

    /** 合同日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractDate;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractStart;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractEnd;

    /** 合同类型 */
    private String contractType;

    /** 应收款 */
    private BigDecimal debtors;

    /** 已收款 */
    private BigDecimal receivable;

    /** 授权委托人 */
    private String principal;

    /** 部门ID */
    private Long deptId;

    /** 对方单位 */
    private String oppositePartyUnit;

    /** 合同文件地址 */
    private String fileAddress;

    /** 合同状态 */
    private String status;

    /** 合同签订地点 */
    private String contractLocation;

    /** 合同简介 */
    private String introduction;

    /** 删除标志（0代表存在 2代表删除）*/
    private String delFlag;

}
