package com.ruoyi.talent.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 人才职位分类对象 sys_talent_post
 *
 * @author kenmi
 * @date 2025-08-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysTalentPost extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 职位id
     */
    private Long id;

    /**
     * 职位名称
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