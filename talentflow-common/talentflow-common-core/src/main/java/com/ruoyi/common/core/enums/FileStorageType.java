package com.ruoyi.common.core.enums;

import lombok.Getter;

@Getter
public enum FileStorageType {
    LOCAL("local"),
    X_FILE_STORAGE("minio"),
    MINIO("xFileStorage");

    private final String type;

    FileStorageType(String type) {
        this.type = type;
    }
}
