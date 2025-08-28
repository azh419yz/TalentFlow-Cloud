package com.ruoyi.interview.service;

import com.ruoyi.interview.domain.SysInterviewRecord;

import java.util.List;

/**
 * 面试记录主Service接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface ISysInterviewRecordService {
    /**
     * 查询面试记录主
     *
     * @param id 面试记录主主键
     * @return 面试记录主
     */
    SysInterviewRecord selectSysInterviewRecordById(Long id);

    /**
     * 查询面试记录主列表
     *
     * @param sysInterviewRecord 面试记录主
     * @return 面试记录主集合
     */
    List<SysInterviewRecord> selectSysInterviewRecordList(SysInterviewRecord sysInterviewRecord);

    /**
     * 新增面试记录主
     *
     * @param sysInterviewRecord 面试记录主
     * @return 结果
     */
    int insertSysInterviewRecord(SysInterviewRecord sysInterviewRecord);

    /**
     * 修改面试记录主
     *
     * @param sysInterviewRecord 面试记录主
     * @return 结果
     */
    int updateSysInterviewRecord(SysInterviewRecord sysInterviewRecord);

    /**
     * 批量删除面试记录主
     *
     * @param ids 需要删除的面试记录主主键集合
     * @return 结果
     */
    int deleteSysInterviewRecordByIds(Long[] ids);

    /**
     * 删除面试记录主信息
     *
     * @param id 面试记录主主键
     * @return 结果
     */
    int deleteSysInterviewRecordById(Long id);
}
