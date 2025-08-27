package com.ruoyi.system.api.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文件记录对象 sys_file_detail
 *
 * @author kenmi
 * @date 2025-08-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysFileDetail extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    private Long id;

    /**
     * 文件访问地址
     */
    @Excel(name = "文件访问地址")
    private String url;

    /**
     * 文件大小，单位字节
     */
    @Excel(name = "文件大小，单位字节")
    private Long size;

    /**
     * 文件名称
     */
    @Excel(name = "文件名称")
    private String filename;

    /**
     * 原始文件名
     */
    @Excel(name = "原始文件名")
    private String originalFilename;

    /**
     * 基础存储路径
     */
    @Excel(name = "基础存储路径")
    private String basePath;

    /**
     * 存储路径
     */
    @Excel(name = "存储路径")
    private String path;

    /**
     * 文件扩展名
     */
    @Excel(name = "文件扩展名")
    private String ext;

    /**
     * MIME类型
     */
    @Excel(name = "MIME类型")
    private String contentType;

    /**
     * 存储平台
     */
    @Excel(name = "存储平台")
    private String platform;

    /**
     * 缩略图访问路径
     */
    @Excel(name = "缩略图访问路径")
    private String thUrl;

    /**
     * 缩略图名称
     */
    @Excel(name = "缩略图名称")
    private String thFilename;

    /**
     * 缩略图大小，单位字节
     */
    @Excel(name = "缩略图大小，单位字节")
    private Long thSize;

    /**
     * 缩略图MIME类型
     */
    @Excel(name = "缩略图MIME类型")
    private String thContentType;

    /**
     * 文件所属对象id
     */
    @Excel(name = "文件所属对象id")
    private String objectId;

    /**
     * 文件所属对象类型，例如用户头像，评价图片
     */
    @Excel(name = "文件所属对象类型，例如用户头像，评价图片")
    private String objectType;
}
