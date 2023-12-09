package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private Integer wageId;

    /** 员工姓名 */
    private String name;

    /** 年月 */
    private Date date;

    /** 实发工资 */
    private BigDecimal money;

    private String company;
}
