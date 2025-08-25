package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.system.domain.SysTalentCandidate;
import com.ruoyi.system.domain.bo.TalentCandidateBo;
import com.ruoyi.system.domain.vo.TalentCandidateVo;
import com.ruoyi.system.mapper.SysTalentCandidateMapper;
import com.ruoyi.system.service.ISysTalentCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
    public TalentCandidateVo selectSysTalentCandidateById(Long id) {
        SysTalentCandidate sysTalentCandidate = sysTalentCandidateMapper.selectById(id);
        if (Objects.isNull(sysTalentCandidate)) {
            throw new ServiceException("所查询的人才不存在");
        }
        TalentCandidateVo vo = new TalentCandidateVo();
        BeanUtils.copyBeanProp(vo, sysTalentCandidate);
        if (StringUtils.isNotEmpty(sysTalentCandidate.getIndustry())) {
            vo.setIndustry(List.of(sysTalentCandidate.getIndustry().split(",")));
        }
        if (StringUtils.isNotEmpty(sysTalentCandidate.getPost())) {
            vo.setPost(List.of(sysTalentCandidate.getPost().split(",")));
        }
        return vo;
    }

    /**
     * 查询人才库列表
     *
     * @param talentCandidateBo 人才库
     * @return 人才库
     */
    @Override
    public List<SysTalentCandidate> selectSysTalentCandidateList(TalentCandidateBo talentCandidateBo) {
        LambdaQueryWrapper<SysTalentCandidate> queryWrapper = new LambdaQueryWrapper<>();
        Map<String, Object> params = talentCandidateBo.getParams();
        boolean expectedSalarySelect = false;
        int expectedSalaryStart = Integer.parseInt((String) params.get("expectedSalaryStart"));
        int expectedSalaryEnd = Integer.parseInt((String) params.get("expectedSalaryEnd"));
        if (expectedSalaryEnd != 0 && expectedSalaryStart <= expectedSalaryEnd) {
            expectedSalarySelect = true;
        }
        queryWrapper.like(StringUtils.isNotEmpty(talentCandidateBo.getName()), SysTalentCandidate::getName, talentCandidateBo.getName())
                .eq(StringUtils.isNotEmpty(talentCandidateBo.getPhoneNumber()), SysTalentCandidate::getPhoneNumber, talentCandidateBo.getPhoneNumber())
                .eq(StringUtils.isNotEmpty(talentCandidateBo.getEmail()), SysTalentCandidate::getEmail, talentCandidateBo.getEmail())
                .eq(Objects.nonNull(talentCandidateBo.getHighestEdu()), SysTalentCandidate::getHighestEdu, talentCandidateBo.getHighestEdu())
                .between(expectedSalarySelect, SysTalentCandidate::getExpectedSalary, expectedSalaryStart, expectedSalaryEnd)
                .orderByDesc(SysTalentCandidate::getUpdateTime);
        return sysTalentCandidateMapper.selectList(queryWrapper);
    }

    /**
     * 新增人才
     *
     * @param talentCandidateBo 人才信息
     * @return 结果
     */
    @Override
    public int insertSysTalentCandidate(TalentCandidateBo talentCandidateBo) {
        SysTalentCandidate sysTalentCandidate = new SysTalentCandidate();
        BeanUtils.copyBeanProp(sysTalentCandidate, talentCandidateBo);
        if (!CollectionUtils.isEmpty(talentCandidateBo.getIndustry())) {
            sysTalentCandidate.setIndustry(String.join(",", talentCandidateBo.getIndustry()));
        }
        if (!CollectionUtils.isEmpty(talentCandidateBo.getPost())) {
            sysTalentCandidate.setPost(String.join(",", talentCandidateBo.getPost()));
        }
        Date nowDate = DateUtils.getNowDate();
        sysTalentCandidate.setCreateTime(nowDate);
        sysTalentCandidate.setUpdateTime(nowDate);
        return sysTalentCandidateMapper.insert(sysTalentCandidate);
    }

    /**
     * 修改人才
     *
     * @param talentCandidateBo 人才
     * @return 结果
     */
    @Override
    public int updateSysTalentCandidate(TalentCandidateBo talentCandidateBo) {
        SysTalentCandidate sysTalentCandidate = new SysTalentCandidate();
        BeanUtils.copyBeanProp(sysTalentCandidate, talentCandidateBo);
        if (!CollectionUtils.isEmpty(talentCandidateBo.getIndustry())) {
            sysTalentCandidate.setIndustry(String.join(",", talentCandidateBo.getIndustry()));
        }
        if (!CollectionUtils.isEmpty(talentCandidateBo.getPost())) {
            sysTalentCandidate.setPost(String.join(",", talentCandidateBo.getPost()));
        }
        sysTalentCandidate.setUpdateTime(DateUtils.getNowDate());
        return sysTalentCandidateMapper.updateById(sysTalentCandidate);
    }

    /**
     * 批量删除人才
     *
     * @param ids 需要删除的人才主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateByIds(Long[] ids) {
        return sysTalentCandidateMapper.deleteByIds(Arrays.asList(ids));
    }

    /**
     * 删除人才信息
     *
     * @param id 人才主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateById(Long id) {
        return sysTalentCandidateMapper.deleteById(id);
    }
}
