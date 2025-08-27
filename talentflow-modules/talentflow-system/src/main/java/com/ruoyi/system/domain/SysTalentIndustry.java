package com.ruoyi.system.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 人才行业分类对象 sys_talent_industry
 *
 * @author kenmi
 * @date 2025-08-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysTalentIndustry extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 行业id
     */
    private Long id;

    /**
     * 行业名称
     */
    private String name;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 类型
     */
    private String type;
}
