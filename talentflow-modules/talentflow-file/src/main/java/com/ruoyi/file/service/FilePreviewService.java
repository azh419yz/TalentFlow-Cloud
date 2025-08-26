package com.ruoyi.file.service;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.file.utils.LibreOfficeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 文件预览服务实现类
 *
 * @author kenmi
 */
@Slf4j
@Service
public class FilePreviewService {

    @Autowired
    private LibreOfficeConverter libreOfficeConverter;

    @Value("${file.path}")
    private String localFilePath;

    @Value("${file.prefix}")
    private String localFilePrefix;

    @Value("${file.domain}")
    private String domain;

    @Value("${preview.temp-dir:${file.path}/file-preview}")
    private String tempDir;

    private final Tika tika = new Tika();

    /**
     * 直接支持预览的文件类型
     */
    public static final List<String> DIRECT_PREVIEW_TYPES = Arrays.asList(
            "pdf", "jpg", "jpeg", "png", "gif", "bmp", "svg", "webp"
    );

    /**
     * PDF转换后缀
     */
    private static final String PDF_SUFFIX = ".pdf";

    public String previewFile(File sourceFile) throws Exception {
        if (sourceFile == null || !sourceFile.exists()) {
            throw new ServiceException("文件不存在");
        }

        String fileName = sourceFile.getName();
        String extension = FilenameUtils.getExtension(fileName).toLowerCase();

        File previewFile;

        // 判断文件预览类型并处理
        if (DIRECT_PREVIEW_TYPES.contains(extension)) {
            // 直接预览类型
            log.debug("直接预览文件: {} (类型: {})", fileName, extension);
            previewFile = sourceFile;
        } else if (libreOfficeConverter.isConvertible(fileName)) {
            // 需要LibreOffice转换为PDF
            log.info("开始转换Office文档: {} -> PDF", fileName);
            previewFile = convertDocumentToPdf(sourceFile, fileName);
            log.info("Office文档转换完成: {}", fileName);
        } else {
            log.warn("不支持预览的文件类型: {} (文件: {})", extension, fileName);
            throw new ServiceException("不支持的预览文件类型");
        }
        return generatePreviewUrl(previewFile);
    }

    /**
     * 生成前端可访问的预览URL
     * 将本地文件路径转换为HTTP访问地址
     *
     * @param previewFile 预览文件（本地文件或转换后的PDF文件）
     * @return 前端可访问的HTTP URL
     */
    private String generatePreviewUrl(File previewFile) {
        String absolutePath = previewFile.getAbsolutePath();
        log.debug("处理文件绝对路径: {}", absolutePath);
        String previewUrl;

        // 检查是否为临时目录中的文件（转换后的PDF文件）
        Path tempDirPath = Paths.get(tempDir);
        String tempDirAbsolutePath = tempDirPath.toAbsolutePath().toString();
        if (absolutePath.startsWith(tempDirAbsolutePath)) {
            // 处理临时目录中的转换文件
            String relativePath = absolutePath.substring(tempDirAbsolutePath.length());
            // 生成临时文件的访问URL
            previewUrl = domain + "/statics/file-preview" + relativePath;
        } else {
            // 检查是否为文件目录中的文件（PDF文件）
            Path fileDirPath = Paths.get(localFilePath);
            String fileDirAbsolutePath = fileDirPath.toAbsolutePath().toString();
            // 处理临时目录中的转换文件
            String relativePath = absolutePath.substring(fileDirAbsolutePath.length());

            // 生成临时文件的访问URL
            previewUrl = domain + "/statics" + relativePath;
        }
        log.debug("生成临时文件预览URL: {}", previewUrl);
        return previewUrl;
    }

    public boolean isSupportedForPreview(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        String extension = FilenameUtils.getExtension(fileName).toLowerCase();
        // 检查直接支持的类型
        if (DIRECT_PREVIEW_TYPES.contains(extension)) {
            log.debug("文件 {} 支持直接预览 (类型: {})", fileName, extension);
            return true;
        }
        // 检查是否可通过LibreOffice转换
        boolean convertible = libreOfficeConverter.isConvertible(fileName);
        if (convertible) {
            log.debug("文件 {} 支持LibreOffice转换预览 (类型: {})", fileName, extension);
        } else {
            log.debug("文件 {} 不支持预览 (类型: {})", fileName, extension);
        }
        return convertible;
    }

    public String getContentType(String fileName) {
        try {
            String detectedType = tika.detect(fileName);
            if (detectedType != null && !detectedType.equals("application/octet-stream")) {
                return detectedType;
            }
        } catch (Exception e) {
            log.warn("Tika检测文件类型失败: {}", e.getMessage());
        }

        // 基于扩展名的备用方案
        String extension = FilenameUtils.getExtension(fileName).toLowerCase();
        return switch (extension) {
            case "pdf" -> "application/pdf";
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "bmp" -> "image/bmp";
            case "svg" -> "image/svg+xml";
            case "webp" -> "image/webp";
            case "doc" -> "application/msword";
            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls" -> "application/vnd.ms-excel";
            case "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt" -> "application/vnd.ms-powerpoint";
            case "pptx" -> "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            case "txt" -> "text/plain";
            default -> "application/octet-stream";
        };
    }

    /**
     * 获取支持的文件类型信息
     */
    public String getSupportedFileTypes() {
        StringBuilder sb = new StringBuilder();
        sb.append("直接预览支持: ");
        sb.append(String.join(", ", DIRECT_PREVIEW_TYPES));
        sb.append("; LibreOffice转换支持: ");
        sb.append("doc, docx, xls, xlsx, ppt, pptx, odt, ods, odp, rtf, txt");
        return sb.toString();
    }

    /**
     * 获取文件预览状态信息
     */
    public String getPreviewStatus(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "文件名为空";
        }
        String extension = FilenameUtils.getExtension(fileName).toLowerCase();
        if (DIRECT_PREVIEW_TYPES.contains(extension)) {
            return "支持直接预览";
        }
        if (libreOfficeConverter.isConvertible(fileName)) {
            boolean libreOfficeAvailable = libreOfficeConverter.isLibreOfficeAvailable();
            return libreOfficeAvailable ? "支持LibreOffice转换预览" : "支持转换但LibreOffice不可用";
        }
        return "不支持预览";
    }

    /**
     * 获取本地文件
     */
    public File getLocalFile(String fileUrl) {
        try {
            // 从URL中提取相对路径
            String relativePath;
            if (fileUrl.startsWith(domain)) {
                // 完整URL
                relativePath = fileUrl.substring(domain.length());
                if (relativePath.startsWith(localFilePrefix)) {
                    relativePath = relativePath.substring(localFilePrefix.length());
                }
            } else if (fileUrl.startsWith(localFilePrefix)) {
                // 相对路径（带前缀）
                relativePath = fileUrl.substring(localFilePrefix.length());
            } else {
                // 相对路径（不带前缀）
                relativePath = fileUrl;
            }

            // 清理路径
            if (relativePath.startsWith("/")) {
                relativePath = relativePath.substring(1);
            }

            File file = new File(localFilePath, relativePath);
            log.debug("本地文件路径: {}", file.getAbsolutePath());
            return file;
        } catch (Exception e) {
            log.error("获取本地文件失败: {}", e.getMessage());
            return null;
        }
    }

    public File getTempFile(String filename) throws Exception {
        // 创建临时目录
        Path tempDirPath = Paths.get(tempDir);
        if (!Files.exists(tempDirPath)) {
            Files.createDirectories(tempDirPath);
        }
        return new File(tempDirPath.toFile(), filename);
    }

    /**
     * 转换文档为PDF（增强版）
     * 支持缓存机制，避免重复转换
     */
    public File convertDocumentToPdf(File sourceFile, String originalFileName) throws Exception {
        // 检查LibreOffice可用性
        if (!libreOfficeConverter.isLibreOfficeAvailable()) {
            log.error("LibreOffice不可用，无法转换文档: {}", originalFileName);
            throw new RuntimeException("LibreOffice服务不可用，请联系管理员检查LibreOffice安装状态");
        }

        // 创建转换输出目录
        Path convertDirPath = Paths.get(tempDir, "converted");
        if (!Files.exists(convertDirPath)) {
            Files.createDirectories(convertDirPath);
            log.debug("创建转换目录: {}", convertDirPath);
        }

        // 生成缓存文件名（基于源文件路径和修改时间）
        String cacheKey = generateCacheKey(sourceFile, originalFileName);
        File cachedPdfFile = new File(convertDirPath.toFile(), cacheKey + PDF_SUFFIX);

        // 检查缓存的PDF文件是否存在且是最新的
        if (cachedPdfFile.exists() && isValidCache(sourceFile, cachedPdfFile)) {
            log.info("使用缓存的PDF文件: {} -> {}", originalFileName, cachedPdfFile.getName());
            return cachedPdfFile;
        }

        // 执行转换
        log.info("执行LibreOffice文档转换: {} (大小: {} bytes)", originalFileName, sourceFile.length());
        long startTime = System.currentTimeMillis();

        try {
            File convertDir = convertDirPath.toFile();
            File convertedFile = libreOfficeConverter.convertToPdf(sourceFile, convertDir);

            // 将转换结果移动到缓存位置
            if (convertedFile.exists() && !convertedFile.equals(cachedPdfFile)) {
                Files.move(convertedFile.toPath(), cachedPdfFile.toPath());
                log.debug("转换文件已缓存: {}", cachedPdfFile.getName());
            }

            long duration = System.currentTimeMillis() - startTime;
            log.info("文档转换成功完成: {} -> {} (耗时: {}ms, 大小: {} bytes)",
                    originalFileName, cachedPdfFile.getName(), duration, cachedPdfFile.length());

            return cachedPdfFile;

        } catch (Exception e) {
            log.error("LibreOffice文档转换失败: {} - {}", originalFileName, e.getMessage(), e);
            // 清理可能的不完整文件
            if (cachedPdfFile.exists()) {
                cachedPdfFile.delete();
            }
            throw new RuntimeException("文档转换失败: " + e.getMessage(), e);
        }
    }


    /**
     * 生成缓存键
     */
    private String generateCacheKey(File sourceFile, String originalFileName) {
        // 使用文件路径、大小和修改时间生成唯一的缓存键
        String input = sourceFile.getAbsolutePath() + "_" + sourceFile.length() + "_" + sourceFile.lastModified();
        return "pdf_" + Math.abs(input.hashCode());
    }

    /**
     * 检查缓存是否有效
     */
    private boolean isValidCache(File sourceFile, File cachedFile) {
        if (!cachedFile.exists()) {
            return false;
        }

        // 检查缓存文件是否比源文件新
        boolean isValid = cachedFile.lastModified() >= sourceFile.lastModified();

        if (!isValid) {
            log.debug("缓存文件已过期，需要重新转换: {}", cachedFile.getName());
        }

        return isValid;
    }

    /**
     * 清理过期的缓存文件
     */
    public void cleanExpiredCache() {
        try {
            Path convertDirPath = Paths.get(tempDir, "converted");
            if (!Files.exists(convertDirPath)) {
                log.debug("转换目录不存在，无需清理: {}", convertDirPath);
                return;
            }

            long currentTime = System.currentTimeMillis();
            long maxAge = 7 * 24 * 60 * 60 * 1000L; // 7天
            int cleanedCount = 0;

            Files.list(convertDirPath)
                    .filter(path -> path.toString().endsWith(PDF_SUFFIX))
                    .filter(path -> {
                        try {
                            return (currentTime - Files.getLastModifiedTime(path).toMillis()) > maxAge;
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            log.debug("清理过期缓存文件: {}", path.getFileName());
                            // cleanedCount++; // 注意：在lambda中无法直接修改局部变量
                        } catch (IOException e) {
                            log.warn("清理缓存文件失败: {}", path.getFileName());
                        }
                    });

            log.info("缓存清理任务完成，处理目录: {}", convertDirPath);

        } catch (Exception e) {
            log.warn("清理缓存过程中发生错误: {}", e.getMessage());
        }
    }

    /**
     * 获取缓存统计信息
     *
     * @return 缓存统计信息
     */
    public String getCacheStatistics() {
        try {
            Path convertDirPath = Paths.get(tempDir, "converted");
            if (!Files.exists(convertDirPath)) {
                return "缓存目录不存在";
            }

            long totalFiles = Files.list(convertDirPath)
                    .filter(path -> path.toString().endsWith(PDF_SUFFIX))
                    .count();

            long totalSize = Files.list(convertDirPath)
                    .filter(path -> path.toString().endsWith(PDF_SUFFIX))
                    .mapToLong(path -> {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            return 0L;
                        }
                    })
                    .sum();

            return String.format("缓存文件数: %d, 总大小: %.2f MB, 目录: %s",
                    totalFiles, totalSize / 1024.0 / 1024.0, convertDirPath);

        } catch (Exception e) {
            log.warn("获取缓存统计信息失败: {}", e.getMessage());
            return "获取缓存统计失败";
        }
    }
}