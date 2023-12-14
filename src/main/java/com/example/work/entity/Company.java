package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@TableName("company")
@ApiModel
public class Company {
    @TableId(type = IdType.AUTO)

    Integer companyId;
    String companyName;
}
