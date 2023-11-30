package com.example.work.entity;

import lombok.Data;

/**
 * 项目对象 pro_project
 *

 */
@Data
public class Project extends BaseEntity{
    private Long projectId;

    /** 项目编号 */
    private String projectNumber;

    /** 项目名称 */
    private String projectName;

    /** 合同id */
    private Long contractId;

    /** 项目类型（1内部 2外部 3科研 4其他） */
    private String projectType;

    /** 项目状态（1待审核 2未启动 3进行中 4已关闭） */
    private String status;

    /** 项目甲方 */
    private String parta;

    /** 项目负责人 */
    private String leader;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

}
