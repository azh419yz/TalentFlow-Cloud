package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.FileServiceStrategy;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author kenmi
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

//    @Autowired
//    private ISysFileService sysFileService;

    @Autowired
    private FileServiceStrategy fileServiceStrategy;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(@RequestPart("file") MultipartFile file,
                             @RequestParam("serviceType") String serviceType) {
        try {
            // 上传并返回访问地址
            ISysFileService sysFileService = fileServiceStrategy.getFileService(serviceType);
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 文件删除请求
     */
    @DeleteMapping("delete")
    public R<Boolean> delete(@RequestParam("fileUrl") String fileUrl,
                             @RequestParam("serviceType") String serviceType) {
        try {
            if (!FileUtils.validateFilePath(fileUrl)) {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许删除。 ", fileUrl));
            }
            ISysFileService sysFileService = fileServiceStrategy.getFileService(serviceType);
            return R.ok(sysFileService.deleteFile(fileUrl));
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(e.getMessage());
        }
    }

}
