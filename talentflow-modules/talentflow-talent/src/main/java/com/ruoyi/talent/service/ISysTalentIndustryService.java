package com.ruoyi.talent.service;

import com.ruoyi.talent.domain.SysTalentIndustry;
import com.ruoyi.talent.domain.vo.TalentIndustryVo;

import java.util.List;

/**
 * 人才行业分类Service接口
 *
 * @author kenmi
 * @date 2025-08-21
 */
public interface ISysTalentIndustryService {
    /**
     * 查询人才行业分类
     *
     * @param id 人才行业分类主键
     * @return 人才行业分类
     */
    SysTalentIndustry selectSysTalentIndustryById(Long id);

    /**
     * 查询人才行业分类列表
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 人才行业分类集合
     */
    List<SysTalentIndustry> selectSysTalentIndustryList(SysTalentIndustry sysTalentIndustry);

    /**
     * 新增人才行业分类
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 结果
     */
    int insertSysTalentIndustry(SysTalentIndustry sysTalentIndustry);

    /**
     * 修改人才行业分类
     *
     * @param sysTalentIndustry 人才行业分类
     * @return 结果
     */
    int updateSysTalentIndustry(SysTalentIndustry sysTalentIndustry);

    /**
     * 批量删除人才行业分类
     *
     * @param ids 需要删除的人才行业分类主键集合
     * @return 结果
     */
    int deleteSysTalentIndustryByIds(Long[] ids);

    /**
     * 删除人才行业分类信息
     *
     * @param id 人才行业分类主键
     * @return 结果
     */
    int deleteSysTalentIndustryById(Long id);

    List<TalentIndustryVo> selectAllIndustries();
}