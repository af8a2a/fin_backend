package com.example.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("examine")
public class Examine {
    //审核主键id
    @TableId(type = IdType.AUTO)
    Integer examineId;
    //外键id
    Integer commitId;
    /** 审核公司 */
    private String examineCompany;

    /** 提交审核日期 */
    private Date examineTime;

    /** 审核类型 */
    private String type;

    //审核金额
    String money;

    /** 审核时间 */
    private Date passTime;

    String status;
}
