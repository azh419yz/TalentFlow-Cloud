package com.ruoyi.position.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 岗位要求详情对象 sys_position_requirement
 *
 * @author kenmi
 * @date 2025-08-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPositionRequirement extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 岗位ID
     */
    private Long positionId;

    /**
     * 最小年龄
     */
    private Long ageMin;

    /**
     * 最大年龄
     */
    private Long ageMax;

    /**
     * 性别要求：MALE男,FEMALE女,UNLIMITED不限
     */
    private String genderRequirement;

    /**
     * 语言要求
     */
    private String languageRequirements;

    /**
     * 证书要求
     */
    private String certificateRequirements;

    /**
     * 出差要求：NONE无,OCCASIONAL偶尔,FREQUENT频繁,EXTENSIVE大量
     */
    private String travelRequirement;

    /**
     * 是否接受加班：1是,0否
     */
    private Long overtimeAcceptable;

    /**
     * 优先经验
     */
    private String preferredExperience;

    /**
     * 优先技能
     */
    private String preferredSkills;

    /**
     * 优先背景
     */
    private String preferredBackground;
}
