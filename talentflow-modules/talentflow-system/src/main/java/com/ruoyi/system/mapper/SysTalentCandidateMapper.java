package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysTalentCandidate;

import java.util.List;

/**
 * 人才库Mapper接口
 *
 * @author ruoyi
 * @date 2025-08-15
 */
public interface SysTalentCandidateMapper {
    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    SysTalentCandidate selectSysTalentCandidateById(Long id);

    /**
     * 查询人才库列表
     *
     * @param sysTalentCandidate 人才库
     * @return 人才库集合
     */
    List<SysTalentCandidate> selectSysTalentCandidateList(SysTalentCandidate sysTalentCandidate);

    /**
     * 新增人才库
     *
     * @param sysTalentCandidate 人才库
     * @return 结果
     */
    int insertSysTalentCandidate(SysTalentCandidate sysTalentCandidate);

    /**
     * 修改人才库
     *
     * @param sysTalentCandidate 人才库
     * @return 结果
     */
    int updateSysTalentCandidate(SysTalentCandidate sysTalentCandidate);

    /**
     * 删除人才库
     *
     * @param id 人才库主键
     * @return 结果
     */
    int deleteSysTalentCandidateById(Long id);

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysTalentCandidateByIds(Long[] ids);
}
