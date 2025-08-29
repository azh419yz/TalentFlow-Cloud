package com.ruoyi.talent.service;

import com.ruoyi.talent.domain.SysTalentPost;
import com.ruoyi.talent.domain.vo.SysTalentPostVo;

import java.util.List;

/**
 * 人才职位分类Service接口
 *
 * @author kenmi
 * @date 2025-08-17
 */
public interface ISysTalentPostService {
    /**
     * 查询人才职位分类
     *
     * @param id 人才职位分类主键
     * @return 人才职位分类
     */
    SysTalentPost selectSysTalentPostById(Long id);

    /**
     * 查询人才职位分类列表
     *
     * @param sysTalentPost 人才职位分类
     * @return 人才职位分类集合
     */
    List<SysTalentPost> selectSysTalentPostList(SysTalentPost sysTalentPost);

    /**
     * 新增人才职位分类
     *
     * @param sysTalentPost 人才职位分类
     * @return 结果
     */
    int insertSysTalentPost(SysTalentPost sysTalentPost);

    /**
     * 修改人才职位分类
     *
     * @param sysTalentPost 人才职位分类
     * @return 结果
     */
    int updateSysTalentPost(SysTalentPost sysTalentPost);

    /**
     * 批量删除人才职位分类
     *
     * @param ids 需要删除的人才职位分类主键集合
     * @return 结果
     */
    int deleteSysTalentPostByIds(Long[] ids);

    /**
     * 删除人才职位分类信息
     *
     * @param id 人才职位分类主键
     * @return 结果
     */
    int deleteSysTalentPostById(Long id);

    List<SysTalentPostVo> selectAllPosts();
}