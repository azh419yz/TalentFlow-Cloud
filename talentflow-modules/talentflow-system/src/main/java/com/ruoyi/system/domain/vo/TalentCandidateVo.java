package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.SysTalentCandidate;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 人才库对象 sys_talent_candidate
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@Data
@AutoMapper(target = SysTalentCandidate.class)
public class TalentCandidateVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 人才id
     */
    private Long id;

    /**
     * 候选人姓名
     */
    @Excel(name = "候选人姓名")
    private String name;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 最高学历
     */
    @Excel(name = "最高学历")
    private Long highestEdu;

    /**
     * 期望年薪
     */
    @Excel(name = "期望年薪")
    private Long expectedSalary;

    /**
     * 行业
     */
    @Excel(name = "行业")
    private String industry;

    /**
     * 职位
     */
    @Excel(name = "职位")
    private String post;

    /**
     * 专业技能
     */
    @Excel(name = "专业技能")
    private String skillTags;

    /**
     * 简历文件名
     */
    @Excel(name = "简历文件名")
    private String resumeFilename;

    /**
     * 简历文件地址
     */
    @Excel(name = "简历文件地址")
    private String resumeUrl;

    private List<String> industryList;

    private List<String> postList;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
