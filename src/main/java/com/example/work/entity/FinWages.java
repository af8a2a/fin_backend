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
public class FinWages {
    /** 工资ID */
    @TableId(type = IdType.AUTO)

    private Long wageId;

    /** 员工工号 */
    private String empCode;

    /** 员工姓名 */
    private String empName;

    /** 年月 */
    private Date issuingDate;

    /** 实发工资 */
    private BigDecimal finalWage;

    private String company;
}
