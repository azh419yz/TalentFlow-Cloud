package com.ruoyi.talent.domain.vo;

import com.ruoyi.talent.domain.SysTalentIndustry;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.List;

/**
 * 人才职位分类对象
 *
 * @author kenmi
 * @date 2025-08-17
 */
@Data
public class SysTalentIndustryVo {

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

    private List<SysTalentIndustryVo> industries;
}