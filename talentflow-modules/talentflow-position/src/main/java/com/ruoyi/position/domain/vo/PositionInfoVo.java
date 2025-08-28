package com.ruoyi.position.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.position.domain.SysPositionInfo;
import com.ruoyi.position.domain.SysPositionRequirement;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 岗位基础信息对象
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Data
@AutoMapper(target = SysPositionInfo.class)
public class PositionInfoVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    private Long id;

    /**
     * 岗位编码
     */
    @Excel(name = "岗位编码")
    private String positionCode;

    /**
     * 岗位标题
     */
    @Excel(name = "岗位标题")
    private String positionTitle;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private Long companyId;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String companyName;

    /**
     * 岗位描述
     */
    @Excel(name = "岗位描述")
    private String positionDescription;

    /**
     * 工作职责
     */
    @Excel(name = "工作职责")
    private String responsibilities;

    /**
     * 任职要求
     */
    @Excel(name = "任职要求")
    private String requirements;

    /**
     * 薪资下限
     */
    @Excel(name = "薪资下限")
    private BigDecimal salaryMin;

    /**
     * 薪资上限
     */
    @Excel(name = "薪资上限")
    private BigDecimal salaryMax;

    /**
     * 薪资单位：MONTH月薪,YEAR年薪,DAY日薪
     */
    @Excel(name = "薪资单位：MONTH月薪,YEAR年薪,DAY日薪")
    private String salaryUnit;

    /**
     * 福利待遇
     */
    @Excel(name = "福利待遇")
    private String benefits;

    /**
     * 工作地点
     */
    @Excel(name = "工作地点")
    private String workLocation;

    /**
     * 工作类型：FULL_TIME全职,PART_TIME兼职,INTERNSHIP实习,CONTRACT合同
     */
    @Excel(name = "工作类型：FULL_TIME全职,PART_TIME兼职,INTERNSHIP实习,CONTRACT合同")
    private String workType;

    /**
     * 工作经验要求
     */
    @Excel(name = "工作经验要求")
    private String workExperience;

    /**
     * 学历要求
     */
    @Excel(name = "学历要求")
    private String educationRequired;

    /**
     * 技能要求
     */
    @Excel(name = "技能要求")
    private String skillsRequired;

    /**
     * 行业标签
     */
    @Excel(name = "行业标签")
    private String industryTags;

    /**
     * 职位标签
     */
    @Excel(name = "职位标签")
    private String positionTags;

    /**
     * 招聘人数
     */
    @Excel(name = "招聘人数")
    private Long recruitNum;

    /**
     * 紧急程度：URGENT紧急,HIGH高,NORMAL普通,LOW低
     */
    @Excel(name = "紧急程度：URGENT紧急,HIGH高,NORMAL普通,LOW低")
    private String urgencyLevel;

    /**
     * 状态：DRAFT草稿,PUBLISHED发布,PAUSED暂停,CLOSED关闭,EXPIRED过期
     */
    @Excel(name = "状态：DRAFT草稿,PUBLISHED发布,PAUSED暂停,CLOSED关闭,EXPIRED过期")
    private String status;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadlineTime;

    /**
     * 关闭原因
     */
    @Excel(name = "关闭原因")
    private String closeReason;

    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数")
    private Long viewCount;

    /**
     * 申请次数
     */
    @Excel(name = "申请次数")
    private Long applyCount;

    /**
     * 面试次数
     */
    @Excel(name = "面试次数")
    private Long interviewCount;

    /**
     * 录用次数
     */
    @Excel(name = "录用次数")
    private Long hireCount;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contactPerson;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String contactPhone;

    /**
     * 联系邮箱
     */
    @Excel(name = "联系邮箱")
    private String contactEmail;

    /**
     * 创建部门
     */
    @Excel(name = "创建部门")
    private Long createDept;

    /**
     * 岗位要求详情信息
     */
    private List<SysPositionRequirement> sysPositionRequirementList;
}
