package com.ruoyi.interview.mapper;

import com.ruoyi.common.datasource.core.mapper.BaseMapperPlus;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import com.ruoyi.interview.domain.vo.InterviewNodeDetailVo;

import java.util.List;

/**
 * 面试节点详情Mapper接口
 *
 * @author kenmi
 * @date 2025-08-28
 */
public interface SysInterviewNodeDetailMapper extends BaseMapperPlus<SysInterviewNodeDetail, InterviewNodeDetailVo> {
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
     * 删除面试节点详情
     *
     * @param id 面试节点详情主键
     * @return 结果
     */
    int deleteSysInterviewNodeDetailById(Long id);

    /**
     * 批量删除面试节点详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysInterviewNodeDetailByIds(Long[] ids);
}
