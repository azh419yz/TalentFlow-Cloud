package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFileDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 文件服务
 *
 * @author kenmi
 */
@FeignClient(contextId = "remoteFileDetailService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteFileDetailService {

    @GetMapping("/fileDetail/{id}")
    R<SysFileDetail> get(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("/fileDetail/byUrl")
    R<SysFileDetail> getByUrl(@RequestParam String url, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/fileDetail")
    R<Boolean> add(@RequestBody SysFileDetail sysFileDetail, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @DeleteMapping("/fileDetail/{id}")
    R<Boolean> remove(@PathVariable Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @DeleteMapping("/fileDetail/byUrl")
    R<Boolean> removeByUrl(@RequestParam String url, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
