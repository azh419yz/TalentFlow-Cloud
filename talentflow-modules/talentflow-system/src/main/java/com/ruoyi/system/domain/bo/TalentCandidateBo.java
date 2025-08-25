package com.ruoyi.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 人才库对象 sys_talent_candidate
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@Data
public class TalentCandidateBo implements Serializable {
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
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 备注
     */
    private String remark;
}
