package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;

/**
 * 人才库对象 sys_talent_candidate
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@Setter
@Getter
public class SysTalentCandidate extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 人才id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 简历文件地址
     */
    @Excel(name = "简历文件地址")
    private String resumeUrl;

    /**
     * 部门ID
     */
    private Long deptId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("phoneNumber", getPhoneNumber())
                .append("email", getEmail())
                .append("highestEdu", getHighestEdu())
                .append("expectedSalary", getExpectedSalary())
                .append("industry", getIndustry())
                .append("skillTags", getSkillTags())
                .append("resumeUrl", getResumeUrl())
                .append("deptId", getDeptId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
