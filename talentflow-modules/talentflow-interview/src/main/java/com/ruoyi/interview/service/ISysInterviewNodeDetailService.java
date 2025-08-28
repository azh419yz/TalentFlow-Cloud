package com.ruoyi.interview.service;

import com.ruoyi.interview.domain.SysInterviewNodeDetail;

import java.util.List;

/**
 * 面试节点详情Service接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface ISysInterviewNodeDetailService {
    /**
     * 查询面试节点详情
     *
     * @param id 面试节点详情主键
     * @return 面试节点详情
     */
    SysInterviewNodeDetail selectSysInterviewNodeDetailById(Long id);

    /**
     * 查询面试节点详情列表
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 面试节点详情集合
     */
    List<SysInterviewNodeDetail> selectSysInterviewNodeDetailList(SysInterviewNodeDetail sysInterviewNodeDetail);

    /**
     * 新增面试节点详情
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 结果
     */
    int insertSysInterviewNodeDetail(SysInterviewNodeDetail sysInterviewNodeDetail);

    /**
     * 修改面试节点详情
     *
     * @param sysInterviewNodeDetail 面试节点详情
     * @return 结果
     */
    int updateSysInterviewNodeDetail(SysInterviewNodeDetail sysInterviewNodeDetail);

    /**
     * 批量删除面试节点详情
     *
     * @param ids 需要删除的面试节点详情主键集合
     * @return 结果
     */
    int deleteSysInterviewNodeDetailByIds(Long[] ids);

    /**
     * 删除面试节点详情信息
     *
     * @param id 面试节点详情主键
     * @return 结果
     */
    int deleteSysInterviewNodeDetailById(Long id);
}
