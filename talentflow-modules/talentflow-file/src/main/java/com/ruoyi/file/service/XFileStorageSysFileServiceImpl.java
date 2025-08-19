package com.ruoyi.file.service;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.system.api.RemoteFileDetailService;
import com.ruoyi.system.api.domain.SysFileDetail;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * XFileStorage 文件存储
 *
 * @author kenmi
 */
//@Primary
@Service("xFileStorageFileService")
public class XFileStorageSysFileServiceImpl implements ISysFileService {

    private static final Logger logger = LoggerFactory.getLogger(XFileStorageSysFileServiceImpl.class);

    @Autowired
    private RemoteFileDetailService remoteFileDetailService;

    @Autowired
    private FileStorageService fileStorage;

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
