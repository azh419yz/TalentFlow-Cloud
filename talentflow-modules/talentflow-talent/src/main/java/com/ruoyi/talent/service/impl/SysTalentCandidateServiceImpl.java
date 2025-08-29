package com.ruoyi.talent.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.ObjectUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.page.PageQuery;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.talent.domain.SysTalentCandidate;
import com.ruoyi.talent.domain.bo.SysTalentCandidateBo;
import com.ruoyi.talent.domain.request.TalentCandidateResumeUpdateRequest;
import com.ruoyi.talent.mapper.SysTalentCandidateMapper;
import com.ruoyi.talent.service.ISysTalentCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 人才库Service业务层处理
 *
 * @author ruoyi
 * @date 2025-08-15
 */
@Service
public class SysTalentCandidateServiceImpl implements ISysTalentCandidateService {
    @Autowired
    private SysTalentCandidateMapper talentCandidateMapper;

    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    @Override
    public SysTalentCandidate selectSysTalentCandidateById(Long id) {
        SysTalentCandidate sysTalentCandidate = talentCandidateMapper.selectById(id);

        if (StringUtils.isNotEmpty(sysTalentCandidate.getIndustry())) {
            sysTalentCandidate.setIndustryList(List.of(sysTalentCandidate.getIndustry().split(",")));
        }
        if (StringUtils.isNotEmpty(sysTalentCandidate.getPost())) {
            sysTalentCandidate.setPostList(List.of(sysTalentCandidate.getPost().split(",")));
        }
        return sysTalentCandidate;
    }

    /**
     * 查询人才库列表
     *
     * @param sysTalentCandidateBo 人才库
     * @return 人才库
     */
    @Override
    public TableDataInfo<SysTalentCandidate> selectPageTalentCandidateList(SysTalentCandidateBo sysTalentCandidateBo, PageQuery pageQuery) {
        Page<SysTalentCandidate> page = talentCandidateMapper.selectPageTalentCandidateList(pageQuery.build(),
                this.buildQueryWrapper(sysTalentCandidateBo));
        page.getRecords().forEach(vo -> {
            if (StringUtils.isNotEmpty(vo.getIndustry())) {
                vo.setIndustryList(List.of(vo.getIndustry().split(",")));
            }
            if (StringUtils.isNotEmpty(vo.getPost())) {
                vo.setPostList(List.of(vo.getPost().split(",")));
            }
        });
        return TableDataInfo.build(page);
    }

    private Wrapper<SysTalentCandidate> buildQueryWrapper(SysTalentCandidateBo sysTalentCandidateBo) {
        QueryWrapper<SysTalentCandidate> wrapper = Wrappers.query();
        Map<String, Object> params = sysTalentCandidateBo.getParams();
        boolean expectedSalarySelect = false;
        int expectedSalaryStart = Integer.parseInt((String) params.get("expectedSalaryStart"));
        int expectedSalaryEnd = Integer.parseInt((String) params.get("expectedSalaryEnd"));
        if (expectedSalaryEnd != 0 && expectedSalaryStart <= expectedSalaryEnd) {
            expectedSalarySelect = true;
        }
        wrapper.like(StringUtils.isNotEmpty(sysTalentCandidateBo.getName()), "name", sysTalentCandidateBo.getName())
                .eq(StringUtils.isNotEmpty(sysTalentCandidateBo.getPhoneNumber()), "phone_number", sysTalentCandidateBo.getPhoneNumber())
                .eq(StringUtils.isNotEmpty(sysTalentCandidateBo.getEmail()), "email", sysTalentCandidateBo.getEmail())
                .eq(Objects.nonNull(sysTalentCandidateBo.getHighestEdu()), "highest_edu", sysTalentCandidateBo.getHighestEdu())
                .between(expectedSalarySelect, "expected_salary", expectedSalaryStart, expectedSalaryEnd)
                .orderByDesc("update_time");
        return wrapper;
    }

    @Override
    public List<SysTalentCandidate> selectTalentCandidateList(SysTalentCandidateBo sysTalentCandidateBo) {
        return talentCandidateMapper.selectList(this.buildQueryWrapper(sysTalentCandidateBo));
    }

    /**
     * 新增人才
     *
     * @param sysTalentCandidateBo 人才信息
     * @return 结果
     */
    @Override
    public int insertSysTalentCandidate(SysTalentCandidateBo sysTalentCandidateBo) {
        SysTalentCandidate talentCandidate = MapstructUtils.convert(sysTalentCandidateBo, SysTalentCandidate.class);
        if (ObjectUtils.isNull(talentCandidate)) throw new ServiceException("人才信息不能为空");
        if (StringUtils.isNotEmpty(sysTalentCandidateBo.getIndustryList())) {
            talentCandidate.setIndustry(String.join(",", sysTalentCandidateBo.getIndustryList()));
        }
        if (StringUtils.isNotEmpty(sysTalentCandidateBo.getPostList())) {
            talentCandidate.setPost(String.join(",", sysTalentCandidateBo.getPostList()));
        }
        return talentCandidateMapper.insert(talentCandidate);
    }

    /**
     * 修改人才
     *
     * @param sysTalentCandidateBo 人才
     * @return 结果
     */
    @Override
    public int updateSysTalentCandidate(SysTalentCandidateBo sysTalentCandidateBo) {
        SysTalentCandidate sysTalentCandidate = new SysTalentCandidate();
        BeanUtils.copyBeanProp(sysTalentCandidate, sysTalentCandidateBo);
        if (StringUtils.isNotEmpty(sysTalentCandidateBo.getIndustryList())) {
            sysTalentCandidate.setIndustry(String.join(",", sysTalentCandidateBo.getIndustryList()));
        }
        if (StringUtils.isNotEmpty(sysTalentCandidateBo.getPostList())) {
            sysTalentCandidate.setPost(String.join(",", sysTalentCandidateBo.getPostList()));
        }
        return talentCandidateMapper.updateById(sysTalentCandidate);
    }

    /**
     * 批量删除人才
     *
     * @param ids 需要删除的人才主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateByIds(Long[] ids) {
        return talentCandidateMapper.deleteByIds(Arrays.asList(ids));
    }

    /**
     * 删除人才信息
     *
     * @param id 人才主键
     * @return 结果
     */
    @Override
    public int deleteSysTalentCandidateById(Long id) {
        return talentCandidateMapper.deleteById(id);
    }

    @Override
    public int updateSysTalentCandidateResume(TalentCandidateResumeUpdateRequest request) {
        UpdateWrapper<SysTalentCandidate> updateWrapper = Wrappers.update();
        updateWrapper.eq("id", request.getId())
                .set("resume_filename", request.getResumeFilename())
                .set("resume_url", request.getResumeUrl());
        return talentCandidateMapper.update(updateWrapper);
    }
}