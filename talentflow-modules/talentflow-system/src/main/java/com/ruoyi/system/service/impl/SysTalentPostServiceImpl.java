package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.system.domain.SysTalentPost;
import com.ruoyi.system.domain.vo.SysTalentPostVo;
import com.ruoyi.system.mapper.SysTalentPostMapper;
import com.ruoyi.system.service.ISysTalentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 人才职位分类Service业务层处理
 *
 * @author kenmi
 * @date 2025-08-17
 */
@Service
public class SysTalentPostServiceImpl implements ISysTalentPostService {
    @Autowired
    private SysTalentPostMapper sysTalentPostMapper;

    /**
     * 查询人才职位分类
     *
     * @param id 人才职位分类主键
     * @return 人才职位分类
     */
    @Override
    public SysTalentPost selectSysTalentPostById(Long id) {
        return sysTalentPostMapper.selectSysTalentPostById(id);
    }

    /**
     * 查询人才职位分类列表
     *
     * @param sysTalentPost 人才职位分类
     * @return 人才职位分类
     */
    @Override
    public List<SysTalentPost> selectSysTalentPostList(SysTalentPost sysTalentPost) {
        return sysTalentPostMapper.selectSysTalentPostList(sysTalentPost);
    }

    /**
     * 新增人才职位分类
     *
     * @param sysTalentPost 人才职位分类
     * @return 结果
     */
    @Override
    public int insertSysTalentPost(SysTalentPost sysTalentPost) {
        sysTalentPost.setCreateTime(DateUtils.getNowDate());
        return sysTalentPostMapper.insertSysTalentPost(sysTalentPost);
    }

    /**
     * 修改人才职位分类
     *
     * @param sysTalentPost 人才职位分类
     * @return 结果
     */
    @Override
    public int updateSysTalentPost(SysTalentPost sysTalentPost) {
        sysTalentPost.setUpdateTime(DateUtils.getNowDate());
        return sysTalentPostMapper.updateSysTalentPost(sysTalentPost);
    }

    /**
     * 批量删除人才职位分类
     *
     * @param ids 需要删除的人才职位分类主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentPostByIds(Long[] ids) {
        return sysTalentPostMapper.deleteSysTalentPostByIds(ids);
    }

    /**
     * 删除人才职位分类信息
     *
     * @param id 人才职位分类主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentPostById(Long id) {
        return sysTalentPostMapper.deleteSysTalentPostById(id);
    }

    @Override
    public List<SysTalentPostVo> selectAllPosts() {
        // 获取category
        List<SysTalentPostVo> categoryList = listPosts(0L, "category");
        List<Long> categoryIds = categoryList.stream().map(SysTalentPostVo::getId).toList();

        // 获取group
        List<SysTalentPostVo> groupList = listPosts(categoryIds, "group");
        List<Long> postIds = groupList.stream().map(SysTalentPostVo::getId).toList();

        // 获取post
        List<SysTalentPostVo> postList = listPosts(postIds, "post");
        Map<Long, List<SysTalentPostVo>> postMap = postList.stream()
                .collect(Collectors.groupingBy(SysTalentPostVo::getParentId));

        // 设置posts
        groupList.forEach(group ->
                group.setPosts(postMap.getOrDefault(group.getId(), Collections.emptyList())));
        Map<Long, List<SysTalentPostVo>> groupMap = groupList.stream()
                .collect(Collectors.groupingBy(SysTalentPostVo::getParentId));

        // 设置groups
        categoryList.forEach(category ->
                category.setGroups(groupMap.getOrDefault(category.getId(), Collections.emptyList())));

        return categoryList;
    }

    private List<SysTalentPostVo> listPosts(Long parentId, String type) {
        return sysTalentPostMapper.selectList(new LambdaQueryWrapper<SysTalentPost>()
                        .eq(SysTalentPost::getParentId, parentId)
                        .eq(SysTalentPost::getType, type))
                .stream()
                .map(post -> {
                    SysTalentPostVo vo = new SysTalentPostVo();
                    BeanUtils.copyBeanProp(vo, post);
                    return vo;
                }).toList();
    }

    private List<SysTalentPostVo> listPosts(List<Long> parentIds, String type) {
        return sysTalentPostMapper.selectList(new LambdaQueryWrapper<SysTalentPost>()
                        .in(SysTalentPost::getParentId, parentIds)
                        .eq(SysTalentPost::getType, type))
                .stream()
                .map(post -> {
                    SysTalentPostVo vo = new SysTalentPostVo();
                    BeanUtils.copyBeanProp(vo, post);
                    return vo;
                }).toList();
    }
}
