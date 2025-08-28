package com.ruoyi.interview.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import com.ruoyi.interview.domain.SysInterviewRecord;
import com.ruoyi.interview.mapper.SysInterviewRecordMapper;
import com.ruoyi.interview.service.ISysInterviewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试记录主Service业务层处理
 *
 * @author kenmi
 * @date 2025-08-28
 */
@Service
public class SysInterviewRecordServiceImpl implements ISysInterviewRecordService {
    @Autowired
    private SysInterviewRecordMapper sysInterviewRecordMapper;

    /**
     * 查询面试记录主
     *
     * @param id 面试记录主主键
     * @return 面试记录主
     */
    @Override
    public SysInterviewRecord selectSysInterviewRecordById(Long id) {
        return sysInterviewRecordMapper.selectSysInterviewRecordById(id);
    }

    /**
     * 查询面试记录主列表
     *
     * @param sysInterviewRecord 面试记录主
     * @return 面试记录主
     */
    @Override
    public List<SysInterviewRecord> selectSysInterviewRecordList(SysInterviewRecord sysInterviewRecord) {
        return sysInterviewRecordMapper.selectSysInterviewRecordList(sysInterviewRecord);
    }

    /**
     * 新增面试记录主
     *
     * @param sysInterviewRecord 面试记录主
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysInterviewRecord(SysInterviewRecord sysInterviewRecord) {
        sysInterviewRecord.setCreateTime(DateUtils.getNowDate());
        int rows = sysInterviewRecordMapper.insertSysInterviewRecord(sysInterviewRecord);
        insertSysInterviewNodeDetail(sysInterviewRecord);
        return rows;
    }

    /**
     * 修改面试记录主
     *
     * @param sysInterviewRecord 面试记录主
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysInterviewRecord(SysInterviewRecord sysInterviewRecord) {
        sysInterviewRecord.setUpdateTime(DateUtils.getNowDate());
        sysInterviewRecordMapper.deleteSysInterviewNodeDetailByInterviewId(sysInterviewRecord.getId());
        insertSysInterviewNodeDetail(sysInterviewRecord);
        return sysInterviewRecordMapper.updateSysInterviewRecord(sysInterviewRecord);
    }

    /**
     * 批量删除面试记录主
     *
     * @param ids 需要删除的面试记录主主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysInterviewRecordByIds(Long[] ids) {
        sysInterviewRecordMapper.deleteSysInterviewNodeDetailByInterviewIds(ids);
        return sysInterviewRecordMapper.deleteSysInterviewRecordByIds(ids);
    }

    /**
     * 删除面试记录主信息
     *
     * @param id 面试记录主主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysInterviewRecordById(Long id) {
        sysInterviewRecordMapper.deleteSysInterviewNodeDetailByInterviewId(id);
        return sysInterviewRecordMapper.deleteSysInterviewRecordById(id);
    }

    /**
     * 新增面试节点详情信息
     *
     * @param sysInterviewRecord 面试记录主对象
     */
    public void insertSysInterviewNodeDetail(SysInterviewRecord sysInterviewRecord) {
        List<SysInterviewNodeDetail> sysInterviewNodeDetailList = sysInterviewRecord.getSysInterviewNodeDetailList();
        Long id = sysInterviewRecord.getId();
        if (StringUtils.isNotNull(sysInterviewNodeDetailList)) {
            List<SysInterviewNodeDetail> list = new ArrayList<SysInterviewNodeDetail>();
            for (SysInterviewNodeDetail sysInterviewNodeDetail : sysInterviewNodeDetailList) {
                sysInterviewNodeDetail.setInterviewId(id);
                list.add(sysInterviewNodeDetail);
            }
            if (list.size() > 0) {
                sysInterviewRecordMapper.batchSysInterviewNodeDetail(list);
            }
        }
    }
}
