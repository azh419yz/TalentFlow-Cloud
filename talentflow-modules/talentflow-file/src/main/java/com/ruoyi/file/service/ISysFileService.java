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
}
