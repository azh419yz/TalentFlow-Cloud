package com.ruoyi.file.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.file.service.FilePreviewService;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.RemoteFileDetailService;
import com.ruoyi.system.api.domain.SysFileDetail;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * XFileStorage 文件存储
 *
 * @author kenmi
 */
//@Primary
@Slf4j
@Service("xFileStorageFileService")
public class XFileStorageSysFileServiceImpl implements ISysFileService {

    private static final Logger logger = LoggerFactory.getLogger(XFileStorageSysFileServiceImpl.class);

    @Autowired
    private RemoteFileDetailService remoteFileDetailService;

    @Autowired
    private FileStorageService fileStorage;

    @Autowired
    private FilePreviewService filePreviewService;

    @Override
    public String uploadFile(MultipartFile file) {
        FileInfo fileInfo = fileStorage.of(file).upload();
        logger.info("上传文件 -> {}", fileInfo);
        if (this.saveFileDetail(fileInfo)) {
            return fileInfo.getUrl();
        }
        return null;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        FileInfo fileInfo = this.getByUrl(fileUrl);
        logger.info("删除文件 -> {}", fileInfo);
        fileStorage.delete(fileInfo);
        return remoteFileDetailService.removeByUrl(fileUrl, SecurityConstants.INNER).getData();
    }

    @Override
    public String previewFile(String fileUrl) throws Exception {
        log.info("预览文件请求 - URL: {}", fileUrl);
        FileInfo fileInfo = this.getByUrl(fileUrl);
        File tempFile = filePreviewService.getTempFile(fileInfo.getFilename());
        if (!tempFile.exists()) {
            // 下载文件
            log.info("下载文件 -> {}", fileInfo);
            fileStorage.download(fileInfo).file(tempFile);
        }
        // 获取文件
        return filePreviewService.previewFile(tempFile);
    }

    @Override
    public boolean isSupportedForPreview(String fileName) {
        return false;
    }

    @Override
    public String getContentType(String fileName) {
        return "";
    }

    @Override
    public String getSupportedFileTypes() {
        return "";
    }

    @Override
    public String getPreviewStatus(String fileName) {
        return "";
    }

    @Override
    public void cleanExpiredCache() {

    }

    @Override
    public String generatePresignedUrl(String fileUrl) {
        FileInfo fileInfo = this.getByUrl(fileUrl);
        return fileStorage.generatePresignedUrl(fileInfo, DateUtil.offsetHour(DateUtils.getNowDate(), 1));
    }

    private boolean saveFileDetail(FileInfo fileInfo) {
        SysFileDetail sysFileDetail = new SysFileDetail();
        BeanUtils.copyProperties(fileInfo, sysFileDetail);
        return remoteFileDetailService.add(sysFileDetail, SecurityConstants.INNER).getData();
    }

    private FileInfo getByUrl(String url) {
        SysFileDetail sysFileDetail = remoteFileDetailService.getByUrl(url, SecurityConstants.INNER).getData();
        FileInfo fileInfo = new FileInfo();
        BeanUtils.copyProperties(sysFileDetail, fileInfo);
        return fileInfo;
    }

}
