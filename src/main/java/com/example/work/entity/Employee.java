package com.example.work.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Employee extends BaseEntity{
    Integer id;
    Integer department_id;
    BigDecimal salary;
    Boolean delFlag;
}
