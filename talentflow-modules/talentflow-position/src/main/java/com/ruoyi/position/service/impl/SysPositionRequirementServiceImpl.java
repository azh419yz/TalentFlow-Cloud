package com.ruoyi.position.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.position.mapper.SysPositionRequirementMapper;
import com.ruoyi.position.domain.SysPositionRequirement;
import com.ruoyi.position.service.ISysPositionRequirementService;

/**
 * 岗位要求详情Service业务层处理
 * 
 * @author kenmi
 * @date 2025-08-28
 */
@Service
public class SysPositionRequirementServiceImpl implements ISysPositionRequirementService 
{
    @Autowired
    private SysPositionRequirementMapper sysPositionRequirementMapper;

    /**
     * 查询岗位要求详情
     * 
     * @param id 岗位要求详情主键
     * @return 岗位要求详情
     */
    @Override
    public SysPositionRequirement selectSysPositionRequirementById(Long id)
    {
        return sysPositionRequirementMapper.selectSysPositionRequirementById(id);
    }

    /**
     * 查询岗位要求详情列表
     * 
     * @param sysPositionRequirement 岗位要求详情
     * @return 岗位要求详情
     */
    @Override
    public List<SysPositionRequirement> selectSysPositionRequirementList(SysPositionRequirement sysPositionRequirement)
    {
        return sysPositionRequirementMapper.selectSysPositionRequirementList(sysPositionRequirement);
    }

    /**
     * 新增岗位要求详情
     * 
     * @param sysPositionRequirement 岗位要求详情
     * @return 结果
     */
    @Override
    public int insertSysPositionRequirement(SysPositionRequirement sysPositionRequirement)
    {
        sysPositionRequirement.setCreateTime(DateUtils.getNowDate());
        return sysPositionRequirementMapper.insertSysPositionRequirement(sysPositionRequirement);
    }

    /**
     * 修改岗位要求详情
     * 
     * @param sysPositionRequirement 岗位要求详情
     * @return 结果
     */
    @Override
    public int updateSysPositionRequirement(SysPositionRequirement sysPositionRequirement)
    {
        sysPositionRequirement.setUpdateTime(DateUtils.getNowDate());
        return sysPositionRequirementMapper.updateSysPositionRequirement(sysPositionRequirement);
    }

    /**
     * 批量删除岗位要求详情
     * 
     * @param ids 需要删除的岗位要求详情主键
     * @return 结果
     */
    @Override
    public int deleteSysPositionRequirementByIds(Long[] ids)
    {
        return sysPositionRequirementMapper.deleteSysPositionRequirementByIds(ids);
    }

    /**
     * 删除岗位要求详情信息
     * 
     * @param id 岗位要求详情主键
     * @return 结果
     */
    @Override
    public int deleteSysPositionRequirementById(Long id)
    {
        return sysPositionRequirementMapper.deleteSysPositionRequirementById(id);
    }
}
