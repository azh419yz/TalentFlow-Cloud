package com.ruoyi.system.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * 人才职位分类对象
 *
 * @author kenmi
 * @date 2025-08-17
 */
@Data
public class TalentIndustryBo {

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

    private List<TalentIndustryBo> industries;

}
