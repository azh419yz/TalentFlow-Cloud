package com.ruoyi.talent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.talent.domain.SysTalentIndustry;
import com.ruoyi.talent.domain.vo.SysTalentIndustryVo;
import com.ruoyi.talent.mapper.SysTalentIndustryMapper;
import com.ruoyi.talent.service.ISysTalentIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 人才行业分类Service业务层处理
 *
 * @author kenmi
 * @date 2025-08-21
 */
@Service
public class SysTalentIndustryServiceImpl implements ISysTalentIndustryService {
    @Autowired
    private SysTalentIndustryMapper sysTalentIndustryMapper;

    /**
     * 查询人才行业分类
     *
     * @param id 人才行业分类主键
     * @return 人才行业分类
     */
    @Override
    public SysTalentIndustry selectSysTalentIndustryById(Long id) {
        return sysTalentIndustryMapper.selectSysTalentIndustryById(id);
    }

    /**
     * 查询人才行业分类列表
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 人才行业分类
     */
    @Override
    public List<SysTalentIndustry> selectSysTalentIndustryList(SysTalentIndustry sysTalentIndustry) {
        return sysTalentIndustryMapper.selectSysTalentIndustryList(sysTalentIndustry);
    }

    /**
     * 新增人才行业分类
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 结果
     */
    @Override
    public int insertSysTalentIndustry(SysTalentIndustry sysTalentIndustry) {
        sysTalentIndustry.setCreateTime(DateUtils.getNowDate());
        return sysTalentIndustryMapper.insertSysTalentIndustry(sysTalentIndustry);
    }

    /**
     * 修改人才行业分类
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 结果
     */
    @Override
    public int updateSysTalentIndustry(SysTalentIndustry sysTalentIndustry) {
        sysTalentIndustry.setUpdateTime(DateUtils.getNowDate());
        return sysTalentIndustryMapper.updateSysTalentIndustry(sysTalentIndustry);
    }

    /**
     * 批量删除人才行业分类
     *
     * @param ids 需要删除的人才行业分类主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentIndustryByIds(Long[] ids) {
        return sysTalentIndustryMapper.deleteSysTalentIndustryByIds(ids);
    }

    /**
     * 删除人才行业分类信息
     *
     * @param id 人才行业分类主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentIndustryById(Long id) {
        return sysTalentIndustryMapper.deleteSysTalentIndustryById(id);
    }

    @Override
    public List<SysTalentIndustryVo> selectAllIndustries() {
        // 获取行业分类
        List<SysTalentIndustryVo> categoryList = sysTalentIndustryMapper.selectList(new LambdaQueryWrapper<SysTalentIndustry>()
                        .eq(SysTalentIndustry::getParentId, 0L)
                        .eq(SysTalentIndustry::getType, "category"))
                .stream()
                .map(industry -> MapstructUtils.convert(industry, SysTalentIndustryVo.class)).toList();
        List<Long> categoryIds = categoryList.stream().map(SysTalentIndustryVo::getId).toList();

        // 获取industry
        List<SysTalentIndustryVo> industryList = sysTalentIndustryMapper.selectList(new LambdaQueryWrapper<SysTalentIndustry>()
                        .in(SysTalentIndustry::getParentId, categoryIds)
                        .eq(SysTalentIndustry::getType, "industry"))
                .stream()
                .map(industry -> MapstructUtils.convert(industry, SysTalentIndustryVo.class)).toList();
        // 按分类id分组
        Map<Long, List<SysTalentIndustryVo>> industryMap = industryList.stream()
                .collect(Collectors.groupingBy(SysTalentIndustryVo::getParentId));

        // 设置industries
        categoryList.forEach(category ->
                category.setIndustries(industryMap.getOrDefault(category.getId(), List.of())));

        return categoryList;
    }
}