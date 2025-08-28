package com.ruoyi.position.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.position.domain.SysPositionRequirement;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 岗位要求详情对象
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Data
@AutoMapper(target = SysPositionRequirement.class)
public class PositionRequirementVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 岗位ID
     */
    @Excel(name = "岗位ID")
    private Long positionId;

    /**
     * 最小年龄
     */
    @Excel(name = "最小年龄")
    private Long ageMin;

    /**
     * 最大年龄
     */
    @Excel(name = "最大年龄")
    private Long ageMax;

    /**
     * 性别要求：MALE男,FEMALE女,UNLIMITED不限
     */
    @Excel(name = "性别要求：MALE男,FEMALE女,UNLIMITED不限")
    private String genderRequirement;

    /**
     * 语言要求
     */
    @Excel(name = "语言要求")
    private String languageRequirements;

    /**
     * 证书要求
     */
    @Excel(name = "证书要求")
    private String certificateRequirements;

    /**
     * 出差要求：NONE无,OCCASIONAL偶尔,FREQUENT频繁,EXTENSIVE大量
     */
    @Excel(name = "出差要求：NONE无,OCCASIONAL偶尔,FREQUENT频繁,EXTENSIVE大量")
    private String travelRequirement;

    /**
     * 是否接受加班：1是,0否
     */
    @Excel(name = "是否接受加班：1是,0否")
    private Long overtimeAcceptable;

    /**
     * 优先经验
     */
    @Excel(name = "优先经验")
    private String preferredExperience;

    /**
     * 优先技能
     */
    @Excel(name = "优先技能")
    private String preferredSkills;

    /**
     * 优先背景
     */
    @Excel(name = "优先背景")
    private String preferredBackground;
}
