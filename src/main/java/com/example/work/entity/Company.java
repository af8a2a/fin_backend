package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data

public class Company {
    @TableId(type = IdType.AUTO)

    Integer companyId;
    String companyName;
}
