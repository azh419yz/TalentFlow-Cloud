package com.ruoyi.file.service;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件存储
 *
 * @author kenmi
 */
//@Primary
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
}
