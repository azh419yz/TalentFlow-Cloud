package com.ruoyi.talent.service;

import com.ruoyi.common.core.web.page.PageQuery;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.talent.domain.SysTalentCandidate;
import com.ruoyi.talent.domain.bo.SysTalentCandidateBo;
import com.ruoyi.talent.domain.request.TalentCandidateResumeUpdateRequest;

import java.util.List;

/**
 * 人才库Service接口
 *
 * @author ruoyi
 * @date 2025-08-15
 */
public interface ISysTalentCandidateService {
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
     * @param sysTalentCandidateBo 人才库
     * @return 人才库集合
     */
    TableDataInfo<SysTalentCandidate> selectPageTalentCandidateList(SysTalentCandidateBo sysTalentCandidateBo, PageQuery pageQuery);

    List<SysTalentCandidate> selectTalentCandidateList(SysTalentCandidateBo sysTalentCandidateBo);

    /**
     * 新增人才库
     *
     * @param sysTalentCandidateBo 人才库
     * @return 结果
     */
    int insertSysTalentCandidate(SysTalentCandidateBo sysTalentCandidateBo);

    /**
     * 修改人才库
     *
     * @param sysTalentCandidateBo 人才库
     * @return 结果
     */
    int updateSysTalentCandidate(SysTalentCandidateBo sysTalentCandidateBo);

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的人才库主键集合
     * @return 结果
     */
    int deleteSysTalentCandidateByIds(Long[] ids);

    /**
     * 删除人才库信息
     *
     * @param id 人才库主键
     * @return 结果
     */
    int deleteSysTalentCandidateById(Long id);

    int updateSysTalentCandidateResume(TalentCandidateResumeUpdateRequest request);
}