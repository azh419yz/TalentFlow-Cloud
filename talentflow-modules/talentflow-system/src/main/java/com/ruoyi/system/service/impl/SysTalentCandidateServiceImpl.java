package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.ObjectUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.page.PageQuery;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.domain.SysTalentCandidate;
import com.ruoyi.system.domain.bo.TalentCandidateBo;
import com.ruoyi.system.domain.request.TalentCandidateResumeUpdateRequest;
import com.ruoyi.system.domain.vo.TalentCandidateVo;
import com.ruoyi.system.mapper.SysTalentCandidateMapper;
import com.ruoyi.system.service.ISysTalentCandidateService;
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
    public TalentCandidateVo selectSysTalentCandidateById(Long id) {
        SysTalentCandidate sysTalentCandidate = talentCandidateMapper.selectById(id);
        TalentCandidateVo vo = MapstructUtils.convert(sysTalentCandidate, TalentCandidateVo.class);
        if (ObjectUtils.isNull(vo)) throw new ServiceException("所查询的人才不存在");

        if (StringUtils.isNotEmpty(sysTalentCandidate.getIndustry())) {
            vo.setIndustryList(List.of(sysTalentCandidate.getIndustry().split(",")));
        }
        if (StringUtils.isNotEmpty(sysTalentCandidate.getPost())) {
            vo.setPostList(List.of(sysTalentCandidate.getPost().split(",")));
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
    public TableDataInfo<TalentCandidateVo> selectPageTalentCandidateList(TalentCandidateBo talentCandidateBo, PageQuery pageQuery) {
        Page<TalentCandidateVo> page = talentCandidateMapper.selectPageTalentCandidateList(pageQuery.build(),
                this.buildQueryWrapper(talentCandidateBo));
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

    private Wrapper<SysTalentCandidate> buildQueryWrapper(TalentCandidateBo talentCandidateBo) {
        QueryWrapper<SysTalentCandidate> wrapper = Wrappers.query();
        Map<String, Object> params = talentCandidateBo.getParams();
        boolean expectedSalarySelect = false;
        int expectedSalaryStart = Integer.parseInt((String) params.get("expectedSalaryStart"));
        int expectedSalaryEnd = Integer.parseInt((String) params.get("expectedSalaryEnd"));
        if (expectedSalaryEnd != 0 && expectedSalaryStart <= expectedSalaryEnd) {
            expectedSalarySelect = true;
        }
        wrapper.like(StringUtils.isNotEmpty(talentCandidateBo.getName()), "name", talentCandidateBo.getName())
                .eq(StringUtils.isNotEmpty(talentCandidateBo.getPhoneNumber()), "phone_number", talentCandidateBo.getPhoneNumber())
                .eq(StringUtils.isNotEmpty(talentCandidateBo.getEmail()), "email", talentCandidateBo.getEmail())
                .eq(Objects.nonNull(talentCandidateBo.getHighestEdu()), "highest_edu", talentCandidateBo.getHighestEdu())
                .between(expectedSalarySelect, "expected_salary", expectedSalaryStart, expectedSalaryEnd)
                .orderByDesc("update_time");
        return wrapper;
    }

    @Override
    public List<TalentCandidateVo> selectTalentCandidateList(TalentCandidateBo talentCandidateBo) {
        List<SysTalentCandidate> sysTalentCandidates = talentCandidateMapper.selectList(this.buildQueryWrapper(talentCandidateBo));
        return MapstructUtils.convert(sysTalentCandidates, TalentCandidateVo.class);
    }

    /**
     * 新增人才
     *
     * @param talentCandidateBo 人才信息
     * @return 结果
     */
    @Override
    public int insertSysTalentCandidate(TalentCandidateBo talentCandidateBo) {
        SysTalentCandidate talentCandidate = MapstructUtils.convert(talentCandidateBo, SysTalentCandidate.class);
        if (ObjectUtils.isNull(talentCandidate)) throw new ServiceException("人才信息不能为空");
        if (StringUtils.isNotEmpty(talentCandidateBo.getIndustryList())) {
            talentCandidate.setIndustry(String.join(",", talentCandidateBo.getIndustryList()));
        }
        if (StringUtils.isNotEmpty(talentCandidateBo.getPostList())) {
            talentCandidate.setPost(String.join(",", talentCandidateBo.getPostList()));
        }
        return talentCandidateMapper.insert(talentCandidate);
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
        if (StringUtils.isNotEmpty(talentCandidateBo.getIndustryList())) {
            sysTalentCandidate.setIndustry(String.join(",", talentCandidateBo.getIndustryList()));
        }
        if (StringUtils.isNotEmpty(talentCandidateBo.getPostList())) {
            sysTalentCandidate.setPost(String.join(",", talentCandidateBo.getPostList()));
        }
        sysTalentCandidate.setUpdateTime(DateUtils.getNowDate());
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
