package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.api.domain.SysFileDetail;
import com.ruoyi.system.mapper.SysFileDetailMapper;
import com.ruoyi.system.service.ISysFileDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件记录Service业务层处理
 *
 * @author kenmi
 * @date 2025-08-13
 */
@Service
public class SysFileDetailServiceImpl implements ISysFileDetailService {
    @Autowired
    private SysFileDetailMapper sysFileDetailMapper;

    /**
     * 查询文件记录
     *
     * @param id 文件记录主键
     * @return 文件记录
     */
    @Override
    public SysFileDetail selectSysFileDetailById(Long id) {
        return sysFileDetailMapper.selectSysFileDetailById(id);
    }

    @Override
    public SysFileDetail selectSysFileDetailByUrl(String url) {
        return sysFileDetailMapper.selectSysFileDetailByUrl(url);
    }

    /**
     * 新增文件记录
     *
     * @param sysFileDetail 文件记录
     * @return 结果
     */
    @Override
    public boolean insertSysFileDetail(SysFileDetail sysFileDetail) {
        sysFileDetail.setCreateTime(DateUtils.getNowDate());
        return sysFileDetailMapper.insertSysFileDetail(sysFileDetail) > 0;
    }

    /**
     * 删除文件记录信息
     *
     * @param id 文件记录主键
     * @return 结果
     */
    @Override
    public boolean deleteSysFileDetailById(Long id) {
        return sysFileDetailMapper.deleteSysFileDetailById(id) > 0;
    }

    @Override
    public Boolean deleteSysFileDetailByUrl(String url) {
        return sysFileDetailMapper.deleteSysFileDetailByUrl(url) > 0;
    }
}
