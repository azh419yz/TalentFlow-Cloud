package com.ruoyi.common.core.enums;

import lombok.Getter;

@Getter
public enum FileServiceType {
    LOCAL("local"),
    X_FILE_STORAGE("minio"),
    MINIO("xFileStorage");

    private final String type;

    FileServiceType(String type) {
        this.type = type;
    }
}
