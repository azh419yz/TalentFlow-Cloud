package com.ruoyi.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 *
 * @author kenmi
 */
public interface ISysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception 当文件上传失败时抛出
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * 文件删除接口
     *
     * @param fileUrl 文件访问URL
     * @return 是否成功
     * @throws Exception 当文件删除失败时抛出
     */
    boolean deleteFile(String fileUrl) throws Exception;

    /**
     * 预览文件
     *
     * @param fileUrl 文件URL地址
     * @return 预览文件的url
     * @throws Exception 当文件预览失败时抛出
     */
    String previewFile(String fileUrl) throws Exception;

    /**
     * 检查文件是否支持预览
     *
     * @param fileName 文件名
     * @return 是否支持预览
     */
    boolean isSupportedForPreview(String fileName);

    /**
     * 获取文件的MIME类型
     *
     * @param fileName 文件名
     * @return MIME类型
     */
    String getContentType(String fileName);

    /**
     * 获取支持的文件类型信息
     *
     * @return 支持的文件类型列表
     */
    String getSupportedFileTypes();

    /**
     * 获取文件预览状态信息
     *
     * @param fileName 文件名
     * @return 预览状态描述
     */
    String getPreviewStatus(String fileName);

    /**
     * 清理过期的缓存文件
     */
    void cleanExpiredCache();

    String generatePresignedUrl(String fileUrl);
}
