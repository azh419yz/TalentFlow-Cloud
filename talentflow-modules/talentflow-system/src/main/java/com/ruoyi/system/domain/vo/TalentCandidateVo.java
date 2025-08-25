package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TalentCandidateVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 人才id
     */
    private Long id;

    /**
     * $column.columnComment
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
    private List<String> industry;

    /**
     * 行业
     */
    private List<String> post;

    /**
     * 专业技能
     */
    private String skillTags;

    /**
     * 简历文件地址
     */
    private String resumeUrl;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

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
