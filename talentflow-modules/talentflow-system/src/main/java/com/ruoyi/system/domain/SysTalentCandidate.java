package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 人才库对象 sys_talent_candidate
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
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
    private String name;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 最高学历
     */
    private Long highestEdu;

    /**
     * 期望年薪
     */
    private Long expectedSalary;

    /**
     * 行业
     */
    private String industry;

    /**
     * 职位
     */
    private String post;

    /**
     * 专业技能
     */
    private String skillTags;

    /**
     * 简历文件名
     */
    private String resumeFilename;

    /**
     * 简历文件地址
     */
    private String resumeUrl;

}
