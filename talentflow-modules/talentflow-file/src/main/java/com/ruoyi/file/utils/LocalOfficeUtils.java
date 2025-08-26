package com.ruoyi.file.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * LibreOffice工具类 - 简化版
 * 适配TalentFlow文件服务
 *
 * @author kenmi
 */
@Component
public class LocalOfficeUtils {

    private static final Logger log = LoggerFactory.getLogger(LocalOfficeUtils.class);

    @Value("${office.home:default}")
    private String officeHomePath;

    // 不同操作系统的LibreOffice可执行文件路径
    private static final String EXECUTABLE_WINDOWS = "program/soffice.exe" ;
    private static final String EXECUTABLE_MAC = "program/soffice" ;
    private static final String EXECUTABLE_MAC_41 = "MacOS/soffice" ;
    private static final String EXECUTABLE_LINUX = "program/soffice.bin" ;

    // 常见的LibreOffice安装路径
    private static final List<String> WINDOWS_PATHS = List.of(
            "C:/Program Files/LibreOffice",
            "C:/Program Files (x86)/LibreOffice"
    );

    private static final List<String> MAC_PATHS = List.of(
            "/Applications/LibreOffice.app/Contents"
    );

    private static final List<String> LINUX_PATHS = List.of(
            "/usr/bin",
            "/usr/local/bin",
            "/opt/libreoffice",
            "/usr/lib/libreoffice",
            "/usr/lib64/libreoffice"
    );

    /**
     * 获取LibreOffice安装目录
     *
     * @return LibreOffice安装目录，如果未找到返回null
     */
    public File getOfficeHome() {
        // 1. 优先使用配置的路径
        if (!"default".equals(officeHomePath)) {
            File configuredHome = new File(officeHomePath);
            if (isValidOfficeHome(configuredHome)) {
                log.debug("使用配置的LibreOffice路径: {}", officeHomePath);
                return configuredHome;
            } else {
                log.warn("配置的LibreOffice路径无效: {}", officeHomePath);
            }
        }

        // 2. 自动检测LibreOffice安装路径
        return detectOfficeHome();
    }

    /**
     * 检查LibreOffice是否可用
     *
     * @return 是否可用
     */
    public boolean isOfficeAvailable() {
        return getOfficeHome() != null;
    }

    /**
     * 获取LibreOffice可执行文件路径
     *
     * @return 可执行文件路径，如果未找到返回null
     */
    public String getOfficeExecutablePath() {
        File officeHome = getOfficeHome();
        if (officeHome == null) {
            return null;
        }

        String os = System.getProperty("os.name").toLowerCase();
        String executable;

        if (os.contains("windows")) {
            executable = EXECUTABLE_WINDOWS;
        } else if (os.contains("mac")) {
            // 尝试两种Mac路径
            File macExec = new File(officeHome, EXECUTABLE_MAC_41);
            if (macExec.exists()) {
                executable = EXECUTABLE_MAC_41;
            } else {
                executable = EXECUTABLE_MAC;
            }
        } else {
            executable = EXECUTABLE_LINUX;
        }

        File execFile = new File(officeHome, executable);
        return execFile.exists() ? execFile.getAbsolutePath() : null;
    }

    /**
     * 自动检测LibreOffice安装目录
     */
    private File detectOfficeHome() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("windows")) {
            return findOfficeInPaths(WINDOWS_PATHS, EXECUTABLE_WINDOWS);
        } else if (os.contains("mac")) {
            // Mac系统优先尝试4.1版本路径
            File result = findOfficeInPaths(MAC_PATHS, EXECUTABLE_MAC_41);
            if (result == null) {
                result = findOfficeInPaths(MAC_PATHS, EXECUTABLE_MAC);
            }
            return result;
        } else {
            // Linux系统
            return findOfficeInPaths(LINUX_PATHS, EXECUTABLE_LINUX);
        }
    }

    /**
     * 在指定路径列表中查找LibreOffice
     */
    private File findOfficeInPaths(List<String> paths, String executable) {
        for (String path : paths) {
            File homeDir = new File(path);
            if (isValidOfficeHome(homeDir, executable)) {
                log.debug("找到LibreOffice安装目录: {}", path);
                return homeDir;
            }
        }
        log.warn("未找到LibreOffice安装目录");
        return null;
    }

    /**
     * 检查是否为有效的LibreOffice安装目录
     */
    private boolean isValidOfficeHome(File homeDir) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("windows")) {
            return isValidOfficeHome(homeDir, EXECUTABLE_WINDOWS);
        } else if (os.contains("mac")) {
            return isValidOfficeHome(homeDir, EXECUTABLE_MAC_41) ||
                    isValidOfficeHome(homeDir, EXECUTABLE_MAC);
        } else {
            return isValidOfficeHome(homeDir, EXECUTABLE_LINUX);
        }
    }

    /**
     * 检查是否为有效的LibreOffice安装目录（指定可执行文件路径）
     */
    private boolean isValidOfficeHome(File homeDir, String executable) {
        if (homeDir == null || !homeDir.exists() || !homeDir.isDirectory()) {
            return false;
        }

        File execFile = new File(homeDir, executable);
        return execFile.exists() && execFile.canExecute();
    }
}
