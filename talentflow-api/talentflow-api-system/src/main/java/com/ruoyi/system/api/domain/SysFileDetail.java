package com.ruoyi.system.api.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;

/**
 * 文件记录对象 sys_file_detail
 *
 * @author kenmi
 * @date 2025-08-13
 */
@Setter
@Getter
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("url", getUrl())
                .append("size", getSize())
                .append("filename", getFilename())
                .append("originalFilename", getOriginalFilename())
                .append("basePath", getBasePath())
                .append("path", getPath())
                .append("ext", getExt())
                .append("contentType", getContentType())
                .append("platform", getPlatform())
                .append("thUrl", getThUrl())
                .append("thFilename", getThFilename())
                .append("thSize", getThSize())
                .append("thContentType", getThContentType())
                .append("objectId", getObjectId())
                .append("objectType", getObjectType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
