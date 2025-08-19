package com.ruoyi.system.mapper;

import com.ruoyi.system.api.domain.SysFileDetail;

/**
 * 文件记录Mapper接口
 *
 * @author kenmi
 * @date 2025-08-13
 */
public interface SysFileDetailMapper {
    /**
     * 查询文件记录
     *
     * @param id 文件记录主键
     * @return 文件记录
     */
    SysFileDetail selectSysFileDetailById(Long id);

    SysFileDetail selectSysFileDetailByUrl(String url);

    /**
     * 新增文件记录
     *
     * @param sysFileDetail 文件记录
     * @return 结果
     */
    int insertSysFileDetail(SysFileDetail sysFileDetail);

    /**
     * 删除文件记录
     *
     * @param id 文件记录主键
     * @return 结果
     */
    int deleteSysFileDetailById(Long id);

    int deleteSysFileDetailByUrl(String url);
}
