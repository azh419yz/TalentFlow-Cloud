package com.ruoyi.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 通用映射配置
 *
 * @author kenmi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 文件预览临时目录
     */
    @Value("${preview.temp-dir:${file.path}/file-preview}")
    private String previewTempDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* 本地文件上传路径 */
        registry.addResourceHandler(localFilePrefix + "/**")
                .addResourceLocations("file:" + localFilePath + File.separator)
                .setCachePeriod(3600); // 设置1小时缓存

        /* 文件预览临时目录路径 */
        registry.addResourceHandler("/statics/file-preview/**")
                .addResourceLocations("file:" + previewTempDir + File.separator)
                .setCachePeriod(3600); // 设置1小时缓存

        /* 处理favicon.ico请求 - 避免预览窗口的404错误 */
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/", "classpath:/public/")
                .setCachePeriod(86400); // 设置1天缓存

        /* 其他静态资源 */
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600);
    }

    /**
     * 开启跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping(localFilePrefix + "/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 设置允许的方法
                .allowedMethods("GET");

        // 为预览临时目录添加跨域支持
        registry.addMapping("/statics/file-preview/**")
                .allowedOrigins("*")
                .allowedMethods("GET");

        // 为favicon添加跨域支持
        registry.addMapping("/favicon.ico")
                .allowedOrigins("*")
                .allowedMethods("GET");
    }
}