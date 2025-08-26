package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.FileServiceStrategy;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author kenmi
 */
@Slf4j
@RestController
public class SysFileController {

    @Autowired
    private FileServiceStrategy fileServiceStrategy;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(@RequestPart("file") MultipartFile file,
                             @RequestParam("storageType") String storageType) {
        try {
            // 上传并返回访问地址
            ISysFileService sysFileService = fileServiceStrategy.getFileService(storageType);
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
     * 获取签名地址
     */
    @GetMapping("signed-url")
    public R<String> signedUrl(@RequestParam("fileUrl") String fileUrl,
                               @RequestParam("storageType") String storageType) {

        //
        ISysFileService sysFileService = fileServiceStrategy.getFileService(storageType);
        return R.ok(sysFileService.generatePresignedUrl(fileUrl), "签名地址");

    }

    /**
     * 文件删除请求
     */
    @DeleteMapping("delete")
    public R<Boolean> delete(@RequestParam("fileUrl") String fileUrl,
                             @RequestParam("storageType") String storageType) {
        try {
            if (!FileUtils.validateFilePath(fileUrl)) {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许删除。 ", fileUrl));
            }
            ISysFileService sysFileService = fileServiceStrategy.getFileService(storageType);
            return R.ok(sysFileService.deleteFile(fileUrl));
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 文件预览请求
     */
    @GetMapping("preview")
    public R<String> preview(@RequestParam("fileUrl") String fileUrl,
                             @RequestParam("storageType") String storageType) {
        try {
            log.info("文件预览请求 - URL: {}, 存储类型: {}", fileUrl, storageType);
            if (StringUtils.isEmpty(fileUrl)) {
                return R.fail();
            }
            ISysFileService sysFileService = fileServiceStrategy.getFileService(storageType);
            return R.ok(sysFileService.previewFile(fileUrl), "预览地址");
        } catch (Exception e) {
            log.error("文件预览失败", e);
            return R.fail();
        }
    }

    /**
     * 检查文件是否支持预览
     */
    @GetMapping("preview/support")
    public R<Boolean> checkPreviewSupport(@RequestParam("fileName") String fileName) {
        try {
            ISysFileService sysFileService = fileServiceStrategy.getFileService("local");
            return R.ok(sysFileService.isSupportedForPreview(fileName), "文件是否支持预览");
        } catch (Exception e) {
            log.error("检查文件预览支持失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 获取支持的文件类型信息
     */
    @GetMapping("preview/types")
    public R<String> getSupportedTypes() {
        try {
            ISysFileService sysFileService = fileServiceStrategy.getFileService("local");
            return R.ok(sysFileService.getSupportedFileTypes(), "支持的文件类型信息");
        } catch (Exception e) {
            log.error("获取支持文件类型失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 获取文件预览状态
     */
    @GetMapping("preview/status")
    public R<String> getPreviewStatus(@RequestParam("fileName") String fileName) {
        try {
            ISysFileService sysFileService = fileServiceStrategy.getFileService("local");
            return R.ok(sysFileService.getPreviewStatus(fileName), "文件预览状态");
        } catch (Exception e) {
            log.error("获取文件预览状态失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 清理预览缓存
     */
    @PostMapping("preview/clean-cache")
    public R<String> cleanPreviewCache() {
        try {
            ISysFileService sysFileService = fileServiceStrategy.getFileService("local");
            sysFileService.cleanExpiredCache();
            return R.ok("缓存清理完成");
        } catch (Exception e) {
            log.error("清理预览缓存失败", e);
            return R.fail(e.getMessage());
        }
    }

}
