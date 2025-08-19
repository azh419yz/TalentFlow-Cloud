package com.ruoyi.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FileServiceStrategy {
    @Autowired
    private final Map<String, ISysFileService> fileServiceMap;

    public FileServiceStrategy(Map<String, ISysFileService> fileServiceMap) {
        this.fileServiceMap = fileServiceMap;
    }

    public ISysFileService getFileService(String type) {
        return fileServiceMap.get(type + "FileService");
    }
}
