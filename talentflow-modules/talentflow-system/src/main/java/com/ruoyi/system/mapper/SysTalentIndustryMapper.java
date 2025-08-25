package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysTalentIndustry;

import java.util.List;

/**
 * 人才行业分类Mapper接口
 *
 * @author kenmi
 * @date 2025-08-21
 */
public interface SysTalentIndustryMapper extends BaseMapper<SysTalentIndustry> {
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
     * 删除人才行业分类
     *
     * @param id 人才行业分类主键
     * @return 结果
     */
    int deleteSysTalentIndustryById(Long id);

    /**
     * 批量删除人才行业分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysTalentIndustryByIds(Long[] ids);
}
