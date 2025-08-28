package com.ruoyi.position.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.position.domain.SysPositionRequirement;
import com.ruoyi.position.mapper.SysPositionInfoMapper;
import com.ruoyi.position.domain.SysPositionInfo;
import com.ruoyi.position.service.ISysPositionInfoService;

/**
 * 岗位基础信息Service业务层处理
 * 
 * @author kenmi
 * @date 2025-08-28
 */
@Service
public class SysPositionInfoServiceImpl implements ISysPositionInfoService 
{
    @Autowired
    private SysPositionInfoMapper sysPositionInfoMapper;

    /**
     * 查询岗位基础信息
     * 
     * @param id 岗位基础信息主键
     * @return 岗位基础信息
     */
    @Override
    public SysPositionInfo selectSysPositionInfoById(Long id)
    {
        return sysPositionInfoMapper.selectSysPositionInfoById(id);
    }

    /**
     * 查询岗位基础信息列表
     * 
     * @param sysPositionInfo 岗位基础信息
     * @return 岗位基础信息
     */
    @Override
    public List<SysPositionInfo> selectSysPositionInfoList(SysPositionInfo sysPositionInfo)
    {
        return sysPositionInfoMapper.selectSysPositionInfoList(sysPositionInfo);
    }

    /**
     * 新增岗位基础信息
     * 
     * @param sysPositionInfo 岗位基础信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysPositionInfo(SysPositionInfo sysPositionInfo)
    {
        sysPositionInfo.setCreateTime(DateUtils.getNowDate());
        int rows = sysPositionInfoMapper.insertSysPositionInfo(sysPositionInfo);
        insertSysPositionRequirement(sysPositionInfo);
        return rows;
    }

    /**
     * 修改岗位基础信息
     * 
     * @param sysPositionInfo 岗位基础信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysPositionInfo(SysPositionInfo sysPositionInfo)
    {
        sysPositionInfo.setUpdateTime(DateUtils.getNowDate());
        sysPositionInfoMapper.deleteSysPositionRequirementByPositionId(sysPositionInfo.getId());
        insertSysPositionRequirement(sysPositionInfo);
        return sysPositionInfoMapper.updateSysPositionInfo(sysPositionInfo);
    }

    /**
     * 批量删除岗位基础信息
     * 
     * @param ids 需要删除的岗位基础信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysPositionInfoByIds(Long[] ids)
    {
        sysPositionInfoMapper.deleteSysPositionRequirementByPositionIds(ids);
        return sysPositionInfoMapper.deleteSysPositionInfoByIds(ids);
    }

    /**
     * 删除岗位基础信息信息
     * 
     * @param id 岗位基础信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysPositionInfoById(Long id)
    {
        sysPositionInfoMapper.deleteSysPositionRequirementByPositionId(id);
        return sysPositionInfoMapper.deleteSysPositionInfoById(id);
    }

    /**
     * 新增岗位要求详情信息
     * 
     * @param sysPositionInfo 岗位基础信息对象
     */
    public void insertSysPositionRequirement(SysPositionInfo sysPositionInfo)
    {
        List<SysPositionRequirement> sysPositionRequirementList = sysPositionInfo.getSysPositionRequirementList();
        Long id = sysPositionInfo.getId();
        if (StringUtils.isNotNull(sysPositionRequirementList))
        {
            List<SysPositionRequirement> list = new ArrayList<SysPositionRequirement>();
            for (SysPositionRequirement sysPositionRequirement : sysPositionRequirementList)
            {
                sysPositionRequirement.setPositionId(id);
                list.add(sysPositionRequirement);
            }
            if (list.size() > 0)
            {
                sysPositionInfoMapper.batchSysPositionRequirement(list);
            }
        }
    }
}
