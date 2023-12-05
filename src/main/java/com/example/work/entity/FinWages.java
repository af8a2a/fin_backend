package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 工资对象 fin_wages
 *
 */
@Data
@TableName("wage")
public class FinWages extends BaseEntity{
    /** 工资ID */
    @TableId(type = IdType.AUTO)

    private Long wageId;

    /** 员工ID */
    private Long empId;

    /** 员工工号 */
    private String empCode;

    /** 员工姓名 */
    private String empName;

    /** 年月 */
    @JsonFormat(pattern = "yyyy-MM")
    private Date issuingDate;

    /** 基础工资 */
    private BigDecimal baseWages;

    /** 奖金 */
    private BigDecimal bonus;

    /** 五险 */
    private BigDecimal insurance;

    /** 住房公积金 */
    private BigDecimal houseFund;

    /** 扣款项 */
    private BigDecimal deducted;

    /** 其他 */
    private BigDecimal others;

    /** 所得税 */
    private BigDecimal tax;

    /** 实发工资 */
    private BigDecimal finalWage;

}
