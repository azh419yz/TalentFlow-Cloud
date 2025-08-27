package com.ruoyi.common.core.exception.auth;

import org.apache.commons.lang3.StringUtils;

import java.io.Serial;

/**
 * 未能通过的角色认证异常
 *
 * @author kenmi
 */
public class NotRoleException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotRoleException(String role) {
        super(role);
    }

    public NotRoleException(String[] roles) {
        super(StringUtils.join(roles, ","));
    }
}
