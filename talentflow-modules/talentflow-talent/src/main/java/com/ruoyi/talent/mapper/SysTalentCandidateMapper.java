package com.ruoyi.talent.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.datasource.core.mapper.BaseMapperPlus;
import com.ruoyi.talent.domain.SysTalentCandidate;
import com.ruoyi.talent.domain.vo.TalentCandidateVo;
import org.apache.ibatis.annotations.Param;

/**
 * 人才库Mapper接口
 *
 * @author ruoyi
 * @date 2025-08-15
 */
public interface SysTalentCandidateMapper extends BaseMapperPlus<SysTalentCandidate, TalentCandidateVo> {
    Page<TalentCandidateVo> selectPageTalentCandidateList(@Param("page") Page<SysTalentCandidate> page, @Param(Constants.WRAPPER) Wrapper<SysTalentCandidate> queryWrapper);
}