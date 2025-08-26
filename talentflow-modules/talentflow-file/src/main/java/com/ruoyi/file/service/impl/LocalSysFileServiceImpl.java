package com.ruoyi.file.service.impl;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.FilePreviewService;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.file.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 本地文件存储
 *
 * @author kenmi
 */
//@Primary
@Slf4j
@Service("localFileService")
public class LocalSysFileServiceImpl implements ISysFileService {
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    @Autowired
    private FilePreviewService filePreviewService;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String name = FileUploadUtils.upload(localFilePath, file);
        return domain + localFilePrefix + name;
    }

    /**
     * 本地文件删除接口
     *
     * @param fileUrl 文件访问URL
     */
    @Override
    public boolean deleteFile(String fileUrl) {
        String localFile = StringUtils.substringAfter(fileUrl, localFilePrefix);
        return FileUtils.deleteFile(localFilePath + localFile);
    }

    @Override
    public String previewFile(String fileUrl) throws Exception {
        log.info("预览文件请求 - URL: {}", fileUrl);

        // 获取文件
        File sourceFile = filePreviewService.getLocalFile(fileUrl);
        return filePreviewService.previewFile(sourceFile);
    }

    @Override
    public boolean isSupportedForPreview(String fileName) {
        return filePreviewService.isSupportedForPreview(fileName);
    }

    @Override
    public String getContentType(String fileName) {
        return filePreviewService.getContentType(fileName);
    }

    @Override
    public String getSupportedFileTypes() {
        return filePreviewService.getSupportedFileTypes();
    }

    @Override
    public String getPreviewStatus(String fileName) {
        return filePreviewService.getPreviewStatus(fileName);
    }

    @Override
    public void cleanExpiredCache() {
        filePreviewService.cleanExpiredCache();
    }

    @Override
    public String generatePresignedUrl(String fileUrl) {
        return fileUrl;
    }
}
