package com.ruoyi.file.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * LibreOffice文档转换工具类
 *
 * @author kenmi
 */
@Component
public class LibreOfficeConverter {

    private static final Logger log = LoggerFactory.getLogger(LibreOfficeConverter.class);

    @Autowired
    private LocalOfficeUtils localOfficeUtils;

    @Value("${office.timeout:60}")
    private int timeoutSeconds;

    /**
     * 支持转换的文件扩展名
     */
    private static final String[] SUPPORTED_EXTENSIONS = {
            "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "odt", "ods", "odp", "rtf", "txt"
    };

    /**
     * 将文档转换为PDF
     *
     * @param inputFile 输入文件
     * @param outputDir 输出目录
     * @return 转换后的PDF文件
     */
    public File convertToPdf(File inputFile, File outputDir) {
        if (!inputFile.exists()) {
            throw new IllegalArgumentException("输入文件不存在: " + inputFile.getAbsolutePath());
        }

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        String fileName = inputFile.getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        File outputFile = new File(outputDir, baseName + ".pdf");

        // 如果PDF文件已存在且是最新的，直接返回
        if (outputFile.exists() && outputFile.lastModified() >= inputFile.lastModified()) {
            log.info("PDF文件已存在且是最新的: {}", outputFile.getAbsolutePath());
            return outputFile;
        }

        // 获取LibreOffice可执行文件路径
        ProcessBuilder processBuilder = getProcessBuilder(inputFile, outputDir);
        log.info("执行LibreOffice转换命令: {}", String.join(" ", processBuilder.command()));

        try {
            Process process = processBuilder.start();
            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);

            if (!finished) {
                process.destroyForcibly();
                throw new RuntimeException("LibreOffice转换超时");
            }

            int exitCode = process.exitValue();
            if (exitCode != 0) {
                throw new RuntimeException("LibreOffice转换失败，退出码: " + exitCode);
            }

            if (!outputFile.exists()) {
                throw new RuntimeException("转换后的PDF文件不存在: " + outputFile.getAbsolutePath());
            }

            log.info("文档转换成功: {} -> {}", inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
            return outputFile;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("LibreOffice转换过程中发生错误", e);
        }
    }

    @NotNull
    private ProcessBuilder getProcessBuilder(File inputFile, File outputDir) {
        String libreOfficePath = localOfficeUtils.getOfficeExecutablePath();
        if (libreOfficePath == null) {
            throw new RuntimeException("LibreOffice未安装或路径配置错误");
        }

        // 构建LibreOffice命令
        ProcessBuilder processBuilder = new ProcessBuilder(
                libreOfficePath,
                "--headless",
                "--convert-to", "pdf",
                "--outdir", outputDir.getAbsolutePath(),
                inputFile.getAbsolutePath()
        );

        processBuilder.redirectErrorStream(true);
        return processBuilder;
    }

    /**
     * 检查文件是否支持转换为PDF
     *
     * @param fileName 文件名
     * @return 是否支持转换
     */
    public boolean isConvertible(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        String extension = getFileExtension(fileName).toLowerCase();
        for (String supportedExt : SUPPORTED_EXTENSIONS) {
            if (supportedExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查LibreOffice是否可用
     *
     * @return LibreOffice是否可用
     */
    public boolean isLibreOfficeAvailable() {
        return localOfficeUtils.isOfficeAvailable();
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }
}