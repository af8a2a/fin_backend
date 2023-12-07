package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("company")
public class Company {
    @TableId(type = IdType.AUTO)

    Integer companyId;
    String companyName;
}
