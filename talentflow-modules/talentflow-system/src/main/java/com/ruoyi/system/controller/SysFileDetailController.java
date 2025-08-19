package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.system.api.domain.SysFileDetail;
import com.ruoyi.system.service.ISysFileDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文件记录Controller
 *
 * @author kenmi
 * @date 2025-08-13
 */
@RestController
@RequestMapping("/fileDetail")
public class SysFileDetailController extends BaseController {
    @Autowired
    private ISysFileDetailService sysFileDetailService;

    /**
     * 获取文件记录详细信息
     */
    @InnerAuth
    @GetMapping(value = "/{id}")
    public R<SysFileDetail> get(@PathVariable("id") Long id) {
        return R.ok(sysFileDetailService.selectSysFileDetailById(id));
    }

    /**
     * 获取文件记录详细信息
     */
    @InnerAuth
    @GetMapping("/byUrl")
    R<SysFileDetail> getByUrl(@RequestParam String url) {
        return R.ok(sysFileDetailService.selectSysFileDetailByUrl(url));
    }

    /**
     * 新增文件记录
     */
    @InnerAuth
    @PostMapping
    public R<Boolean> add(@RequestBody SysFileDetail sysFileDetail) {
        return R.ok(sysFileDetailService.insertSysFileDetail(sysFileDetail));
    }

    /**
     * 删除文件记录
     */
    @InnerAuth
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable Long id) {
        return R.ok(sysFileDetailService.deleteSysFileDetailById(id));
    }

    /**
     * 删除文件记录
     */
    @InnerAuth
    @DeleteMapping("/byUrl")
    R<Boolean> removeByUrl(@RequestParam String url) {
        return R.ok(sysFileDetailService.deleteSysFileDetailByUrl(url));
    }
}
