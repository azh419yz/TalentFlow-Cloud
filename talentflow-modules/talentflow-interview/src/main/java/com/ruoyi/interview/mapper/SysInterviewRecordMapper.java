package com.ruoyi.interview.mapper;

import com.ruoyi.common.datasource.core.mapper.BaseMapperPlus;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import com.ruoyi.interview.domain.SysInterviewRecord;
import com.ruoyi.interview.domain.vo.InterviewRecordVo;

import java.util.List;

/**
 * 面试记录主Mapper接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface SysInterviewRecordMapper extends BaseMapperPlus<SysInterviewRecord, InterviewRecordVo> {
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
     * 删除面试记录主
     *
     * @param id 面试记录主主键
     * @return 结果
     */
    int deleteSysInterviewRecordById(Long id);

    /**
     * 批量删除面试记录主
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysInterviewRecordByIds(Long[] ids);

    /**
     * 批量删除面试节点详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysInterviewNodeDetailByInterviewIds(Long[] ids);

    /**
     * 批量新增面试节点详情
     *
     * @param sysInterviewNodeDetailList 面试节点详情列表
     * @return 结果
     */
    int batchSysInterviewNodeDetail(List<SysInterviewNodeDetail> sysInterviewNodeDetailList);


    /**
     * 通过面试记录主主键删除面试节点详情信息
     *
     * @param id 面试记录主ID
     * @return 结果
     */
    int deleteSysInterviewNodeDetailByInterviewId(Long id);
}
