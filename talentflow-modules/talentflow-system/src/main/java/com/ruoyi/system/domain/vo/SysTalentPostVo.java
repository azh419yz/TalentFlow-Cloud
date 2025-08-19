package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 人才职位分类对象
 *
 * @author kenmi
 * @date 2025-08-17
 */
@Data
public class SysTalentPostVo {

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

    private List<SysTalentPostVo> groups;

    private List<SysTalentPostVo> posts;

}
