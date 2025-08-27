package com.ruoyi.gateway.service;

import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author kenmi
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCaptcha(String key, String value) throws CaptchaException;
}
