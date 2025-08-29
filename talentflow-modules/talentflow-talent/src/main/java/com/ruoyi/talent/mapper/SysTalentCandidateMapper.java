package com.ruoyi.talent.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.talent.domain.SysTalentCandidate;
import org.apache.ibatis.annotations.Param;

/**
 * 人才库Mapper接口
 *
 * @author ruoyi
 * @date 2025-08-15
 */
public interface SysTalentCandidateMapper extends BaseMapper<SysTalentCandidate> {
    Page<SysTalentCandidate> selectPageTalentCandidateList(@Param("page") Page<SysTalentCandidate> page, @Param(Constants.WRAPPER) Wrapper<SysTalentCandidate> queryWrapper);
}