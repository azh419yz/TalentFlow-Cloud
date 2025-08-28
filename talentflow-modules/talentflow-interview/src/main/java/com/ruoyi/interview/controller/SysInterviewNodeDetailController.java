package com.ruoyi.interview.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.interview.domain.SysInterviewNodeDetail;
import com.ruoyi.interview.service.ISysInterviewNodeDetailService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 面试节点详情Controller
 *
 * @author kenmi
 * @date 2025-08-28
 */
@RestController
@RequestMapping("/detail")
public class SysInterviewNodeDetailController extends BaseController {
    @Autowired
    private ISysInterviewNodeDetailService sysInterviewNodeDetailService;

    /**
     * 查询面试节点详情列表
     */
    @RequiresPermissions("interview:detail:list")
    @GetMapping("/list")
    public TableDataInfo list(SysInterviewNodeDetail sysInterviewNodeDetail) {
        startPage();
        List<SysInterviewNodeDetail> list = sysInterviewNodeDetailService.selectSysInterviewNodeDetailList(sysInterviewNodeDetail);
        return getDataTable(list);
    }

    /**
     * 导出面试节点详情列表
     */
    @RequiresPermissions("interview:detail:export")
    @Log(title = "面试节点详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInterviewNodeDetail sysInterviewNodeDetail) {
        List<SysInterviewNodeDetail> list = sysInterviewNodeDetailService.selectSysInterviewNodeDetailList(sysInterviewNodeDetail);
        ExcelUtil<SysInterviewNodeDetail> util = new ExcelUtil<SysInterviewNodeDetail>(SysInterviewNodeDetail.class);
        util.exportExcel(response, list, "面试节点详情数据");
    }

    /**
     * 获取面试节点详情详细信息
     */
    @RequiresPermissions("interview:detail:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysInterviewNodeDetailService.selectSysInterviewNodeDetailById(id));
    }

    /**
     * 新增面试节点详情
     */
    @RequiresPermissions("interview:detail:add")
    @Log(title = "面试节点详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInterviewNodeDetail sysInterviewNodeDetail) {
        return toAjax(sysInterviewNodeDetailService.insertSysInterviewNodeDetail(sysInterviewNodeDetail));
    }

    /**
     * 修改面试节点详情
     */
    @RequiresPermissions("interview:detail:edit")
    @Log(title = "面试节点详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInterviewNodeDetail sysInterviewNodeDetail) {
        return toAjax(sysInterviewNodeDetailService.updateSysInterviewNodeDetail(sysInterviewNodeDetail));
    }

    /**
     * 删除面试节点详情
     */
    @RequiresPermissions("interview:detail:remove")
    @Log(title = "面试节点详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysInterviewNodeDetailService.deleteSysInterviewNodeDetailByIds(ids));
    }
}
