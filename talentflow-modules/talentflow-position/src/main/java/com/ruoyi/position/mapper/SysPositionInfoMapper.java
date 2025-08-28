package com.ruoyi.position.mapper;

import com.ruoyi.common.datasource.core.mapper.BaseMapperPlus;
import com.ruoyi.position.domain.SysPositionInfo;
import com.ruoyi.position.domain.SysPositionRequirement;
import com.ruoyi.position.domain.vo.PositionInfoVo;

import java.util.List;

/**
 * 岗位基础信息Mapper接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface SysPositionInfoMapper extends BaseMapperPlus<SysPositionInfo, PositionInfoVo> {
    /**
     * 查询岗位基础信息
     *
     * @param id 岗位基础信息主键
     * @return 岗位基础信息
     */
    SysPositionInfo selectSysPositionInfoById(Long id);

    /**
     * 查询岗位基础信息列表
     *
     * @param sysPositionInfo 岗位基础信息
     * @return 岗位基础信息集合
     */
    List<SysPositionInfo> selectSysPositionInfoList(SysPositionInfo sysPositionInfo);

    /**
     * 新增岗位基础信息
     *
     * @param sysPositionInfo 岗位基础信息
     * @return 结果
     */
    int insertSysPositionInfo(SysPositionInfo sysPositionInfo);

    /**
     * 修改岗位基础信息
     *
     * @param sysPositionInfo 岗位基础信息
     * @return 结果
     */
    int updateSysPositionInfo(SysPositionInfo sysPositionInfo);

    /**
     * 删除岗位基础信息
     *
     * @param id 岗位基础信息主键
     * @return 结果
     */
    int deleteSysPositionInfoById(Long id);

    /**
     * 批量删除岗位基础信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysPositionInfoByIds(Long[] ids);

    /**
     * 批量删除岗位要求详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysPositionRequirementByPositionIds(Long[] ids);

    /**
     * 批量新增岗位要求详情
     *
     * @param sysPositionRequirementList 岗位要求详情列表
     * @return 结果
     */
    int batchSysPositionRequirement(List<SysPositionRequirement> sysPositionRequirementList);

    /**
     * 通过岗位基础信息主键删除岗位要求详情信息
     *
     * @param id 岗位基础信息ID
     * @return 结果
     */
    int deleteSysPositionRequirementByPositionId(Long id);
}
