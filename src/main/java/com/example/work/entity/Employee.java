package com.example.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Employee extends BaseEntity{
    /** 员工ID */
    private Long empId;

    /** 工号 */
    private String empCode;

    /** 姓名 */
    private String empName;

    /** 性别 */
    private String sex;

    /** 状态 */
    private String status;

    /** 学历 */
    private String education;

    /** 电话 */
    private String phonenumber;

    /** 邮箱 */
    private String email;

    /** 住址 */
    private String address;

    /** 部门ID */
    private Long deptId;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hiredate;

    /** 离职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date termdate;

    /** 基础工资 */
    private BigDecimal baseWages;

    /** 住房公积金基数 */
    private String houseWages;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 岗位组 */
    private Long[] postIds;

    private Department dept;
}
