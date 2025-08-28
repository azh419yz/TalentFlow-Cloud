package com.ruoyi.position.service;

import com.ruoyi.position.domain.SysPositionRequirement;

import java.util.List;

/**
 * 岗位要求详情Service接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface ISysPositionRequirementService {
    /**
     * 查询岗位要求详情
     *
     * @param id 岗位要求详情主键
     * @return 岗位要求详情
     */
    SysPositionRequirement selectSysPositionRequirementById(Long id);

    /**
     * 查询岗位要求详情列表
     *
     * @param sysPositionRequirement 岗位要求详情
     * @return 岗位要求详情集合
     */
    List<SysPositionRequirement> selectSysPositionRequirementList(SysPositionRequirement sysPositionRequirement);

    /**
     * 新增岗位要求详情
     *
     * @param sysPositionRequirement 岗位要求详情
     * @return 结果
     */
    int insertSysPositionRequirement(SysPositionRequirement sysPositionRequirement);

    /**
     * 修改岗位要求详情
     *
     * @param sysPositionRequirement 岗位要求详情
     * @return 结果
     */
    int updateSysPositionRequirement(SysPositionRequirement sysPositionRequirement);

    /**
     * 批量删除岗位要求详情
     *
     * @param ids 需要删除的岗位要求详情主键集合
     * @return 结果
     */
    int deleteSysPositionRequirementByIds(Long[] ids);

    /**
     * 删除岗位要求详情信息
     *
     * @param id 岗位要求详情主键
     * @return 结果
     */
    int deleteSysPositionRequirementById(Long id);
}
