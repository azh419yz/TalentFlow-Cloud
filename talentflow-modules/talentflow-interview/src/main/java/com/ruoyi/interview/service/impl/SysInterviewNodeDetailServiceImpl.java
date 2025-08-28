package com.ruoyi.interview.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import com.ruoyi.interview.mapper.SysInterviewNodeDetailMapper;
import com.ruoyi.interview.service.ISysInterviewNodeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试节点详情Service业务层处理
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Service
public class SysInterviewNodeDetailServiceImpl implements ISysInterviewNodeDetailService {
    @Autowired
    private SysInterviewNodeDetailMapper sysInterviewNodeDetailMapper;

    /**
     * 查询面试节点详情
     *
     * @param id 面试节点详情主键
     * @return 面试节点详情
     */
    @Override
    public SysInterviewNodeDetail selectSysInterviewNodeDetailById(Long id) {
        return sysInterviewNodeDetailMapper.selectSysInterviewNodeDetailById(id);
    }

    /**
     * 查询面试节点详情列表
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 面试节点详情
     */
    @Override
    public List<SysInterviewNodeDetail> selectSysInterviewNodeDetailList(SysInterviewNodeDetail sysInterviewNodeDetail) {
        return sysInterviewNodeDetailMapper.selectSysInterviewNodeDetailList(sysInterviewNodeDetail);
    }

    /**
     * 新增面试节点详情
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 结果
     */
    @Override
    public int insertSysInterviewNodeDetail(SysInterviewNodeDetail sysInterviewNodeDetail) {
        sysInterviewNodeDetail.setCreateTime(DateUtils.getNowDate());
        return sysInterviewNodeDetailMapper.insertSysInterviewNodeDetail(sysInterviewNodeDetail);
    }

    /**
     * 修改面试节点详情
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 结果
     */
    @Override
    public int updateSysInterviewNodeDetail(SysInterviewNodeDetail sysInterviewNodeDetail) {
        sysInterviewNodeDetail.setUpdateTime(DateUtils.getNowDate());
        return sysInterviewNodeDetailMapper.updateSysInterviewNodeDetail(sysInterviewNodeDetail);
    }

    /**
     * 批量删除面试节点详情
     *
     * @param ids 需要删除的面试节点详情主键
     * @return 结果
     */
    @Override
    public int deleteSysInterviewNodeDetailByIds(Long[] ids) {
        return sysInterviewNodeDetailMapper.deleteSysInterviewNodeDetailByIds(ids);
    }

    /**
     * 删除面试节点详情信息
     *
     * @param id 面试节点详情主键
     * @return 结果
     */
    @Override
    public int deleteSysInterviewNodeDetailById(Long id) {
        return sysInterviewNodeDetailMapper.deleteSysInterviewNodeDetailById(id);
    }
}
