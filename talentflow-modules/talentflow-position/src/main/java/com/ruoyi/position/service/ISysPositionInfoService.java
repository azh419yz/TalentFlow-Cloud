package com.ruoyi.position.service;

import com.ruoyi.position.domain.SysPositionInfo;

import java.util.List;

/**
 * 岗位基础信息Service接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface ISysPositionInfoService {
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
     * 批量删除岗位基础信息
     *
     * @param ids 需要删除的岗位基础信息主键集合
     * @return 结果
     */
    int deleteSysPositionInfoByIds(Long[] ids);

    /**
     * 删除岗位基础信息信息
     *
     * @param id 岗位基础信息主键
     * @return 结果
     */
    int deleteSysPositionInfoById(Long id);
}
