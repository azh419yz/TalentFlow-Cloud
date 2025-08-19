package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.SysTalentCandidate;
import com.ruoyi.system.mapper.SysTalentCandidateMapper;
import com.ruoyi.system.service.ISysTalentCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才库Service业务层处理
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@Service
public class SysTalentCandidateServiceImpl implements ISysTalentCandidateService {
    @Autowired
    private SysTalentCandidateMapper sysTalentCandidateMapper;

    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    @Override
    public SysTalentCandidate selectSysTalentCandidateById(Long id) {
        return sysTalentCandidateMapper.selectSysTalentCandidateById(id);
    }

    /**
     * 查询人才库列表
     *
     * @param sysTalentCandidate 人才库
     * @return 人才库
     */
    @Override
    public List<SysTalentCandidate> selectSysTalentCandidateList(SysTalentCandidate sysTalentCandidate) {
        return sysTalentCandidateMapper.selectSysTalentCandidateList(sysTalentCandidate);
    }

    /**
     * 新增人才库
     *
     * @param sysTalentCandidate 人才库
     * @return 结果
     */
    @Override
    public int insertSysTalentCandidate(SysTalentCandidate sysTalentCandidate) {
        sysTalentCandidate.setCreateTime(DateUtils.getNowDate());
        return sysTalentCandidateMapper.insertSysTalentCandidate(sysTalentCandidate);
    }

    /**
     * 修改人才库
     *
     * @param sysTalentCandidate 人才库
     * @return 结果
     */
    @Override
    public int updateSysTalentCandidate(SysTalentCandidate sysTalentCandidate) {
        sysTalentCandidate.setUpdateTime(DateUtils.getNowDate());
        return sysTalentCandidateMapper.updateSysTalentCandidate(sysTalentCandidate);
    }

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的人才库主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateByIds(Long[] ids) {
        return sysTalentCandidateMapper.deleteSysTalentCandidateByIds(ids);
    }

    /**
     * 删除人才库信息
     *
     * @param id 人才库主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateById(Long id) {
        return sysTalentCandidateMapper.deleteSysTalentCandidateById(id);
    }
}
