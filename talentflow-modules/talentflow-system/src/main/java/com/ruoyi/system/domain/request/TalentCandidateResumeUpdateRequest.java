package com.ruoyi.system.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TalentCandidateResumeUpdateRequest {

    @NotNull(message = "人才ID不能为空")
    private Long id;

    private String resumeFilename;
    private String resumeUrl;
}
