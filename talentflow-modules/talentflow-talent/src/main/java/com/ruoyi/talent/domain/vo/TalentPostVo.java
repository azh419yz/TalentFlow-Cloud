package com.ruoyi.talent.domain.vo;

import com.ruoyi.talent.domain.SysTalentPost;
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
@AutoMapper(target = SysTalentPost.class)
public class TalentPostVo {

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

    private List<TalentPostVo> groups;

    private List<TalentPostVo> posts;
}