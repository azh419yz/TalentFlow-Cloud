package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.system.domain.SysTalentIndustry;
import com.ruoyi.system.domain.vo.TalentIndustryVo;
import com.ruoyi.system.mapper.SysTalentIndustryMapper;
import com.ruoyi.system.service.ISysTalentIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<TalentIndustryVo> selectAllIndustries() {
        // 获取行业分类
        List<TalentIndustryVo> categoryList = sysTalentIndustryMapper.selectList(new LambdaQueryWrapper<SysTalentIndustry>()
                        .eq(SysTalentIndustry::getParentId, 0L)
                        .eq(SysTalentIndustry::getType, "category"))
                .stream()
                .map(industry -> {
                    TalentIndustryVo vo = new TalentIndustryVo();
                    BeanUtils.copyBeanProp(vo, industry);
                    return vo;
                }).toList();
        List<Long> categoryIds = categoryList.stream().map(TalentIndustryVo::getId).toList();

        // 获取industry
        List<TalentIndustryVo> industryList = sysTalentIndustryMapper.selectList(new LambdaQueryWrapper<SysTalentIndustry>()
                        .in(SysTalentIndustry::getParentId, categoryIds)
                        .eq(SysTalentIndustry::getType, "industry"))
                .stream()
                .map(industry -> {
                    TalentIndustryVo vo = new TalentIndustryVo();
                    BeanUtils.copyBeanProp(vo, industry);
                    return vo;
                }).toList();
        // 按分类id分组
        Map<Long, List<TalentIndustryVo>> industryMap = industryList.stream()
                .collect(Collectors.groupingBy(TalentIndustryVo::getParentId));
        // 设置industries
        categoryList.forEach(category -> category.setIndustries(industryMap.getOrDefault(category.getId(), Collections.emptyList())));
        return categoryList;
    }
}
