package com.ruoyi.interview.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.interview.domain.SysInterviewRecord;
import com.ruoyi.interview.service.ISysInterviewRecordService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 面试记录主Controller
 *
 * @author kenmi
 * @date 2025-08-28
 */
@RestController
@RequestMapping("/record")
public class SysInterviewRecordController extends BaseController {
    @Autowired
    private ISysInterviewRecordService sysInterviewRecordService;

    /**
     * 查询面试记录主列表
     */
    @RequiresPermissions("interview:record:list")
    @GetMapping("/list")
    public TableDataInfo list(SysInterviewRecord sysInterviewRecord) {
        startPage();
        List<SysInterviewRecord> list = sysInterviewRecordService.selectSysInterviewRecordList(sysInterviewRecord);
        return getDataTable(list);
    }

    /**
     * 导出面试记录主列表
     */
    @RequiresPermissions("interview:record:export")
    @Log(title = "面试记录主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInterviewRecord sysInterviewRecord) {
        List<SysInterviewRecord> list = sysInterviewRecordService.selectSysInterviewRecordList(sysInterviewRecord);
        ExcelUtil<SysInterviewRecord> util = new ExcelUtil<SysInterviewRecord>(SysInterviewRecord.class);
        util.exportExcel(response, list, "面试记录主数据");
    }

    /**
     * 获取面试记录主详细信息
     */
    @RequiresPermissions("interview:record:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysInterviewRecordService.selectSysInterviewRecordById(id));
    }

    /**
     * 新增面试记录主
     */
    @RequiresPermissions("interview:record:add")
    @Log(title = "面试记录主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInterviewRecord sysInterviewRecord) {
        return toAjax(sysInterviewRecordService.insertSysInterviewRecord(sysInterviewRecord));
    }

    /**
     * 修改面试记录主
     */
    @RequiresPermissions("interview:record:edit")
    @Log(title = "面试记录主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInterviewRecord sysInterviewRecord) {
        return toAjax(sysInterviewRecordService.updateSysInterviewRecord(sysInterviewRecord));
    }

    /**
     * 删除面试记录主
     */
    @RequiresPermissions("interview:record:remove")
    @Log(title = "面试记录主", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysInterviewRecordService.deleteSysInterviewRecordByIds(ids));
    }
}
