package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysTalentCandidate;
import com.ruoyi.system.domain.bo.TalentCandidateBo;

import java.util.List;

/**
 * 人才库Mapper接口
 *
 * @author ruoyi
 * @date 2025-08-15
 */
public interface SysTalentCandidateMapper extends BaseMapper<SysTalentCandidate> {

    /**
     * 查询人才库列表
     *
     * @param talentCandidateBo 人才库
     * @return 人才库集合
     */
    List<SysTalentCandidate> selectSysTalentCandidateList(TalentCandidateBo talentCandidateBo);

}
