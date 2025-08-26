package com.ruoyi.system.service;

import com.ruoyi.common.core.web.page.PageQuery;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.domain.bo.TalentCandidateBo;
import com.ruoyi.system.domain.vo.TalentCandidateVo;

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
    TalentCandidateVo selectSysTalentCandidateById(Long id);

    /**
     * 查询人才库列表
     *
     * @param talentCandidateBo 人才库
     * @return 人才库集合
     */
    TableDataInfo<TalentCandidateVo> selectPageTalentCandidateList(TalentCandidateBo talentCandidateBo, PageQuery pageQuery);

    List<TalentCandidateVo> selectTalentCandidateList(TalentCandidateBo talentCandidateBo);

    /**
     * 新增人才库
     *
     * @param talentCandidateBo 人才库
     * @return 结果
     */
    int insertSysTalentCandidate(TalentCandidateBo talentCandidateBo);

    /**
     * 修改人才库
     *
     * @param talentCandidateBo 人才库
     * @return 结果
     */
    int updateSysTalentCandidate(TalentCandidateBo talentCandidateBo);

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
}
