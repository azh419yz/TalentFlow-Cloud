package com.ruoyi.position.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.position.domain.SysPositionInfo;
import com.ruoyi.position.service.ISysPositionInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位基础信息Controller
 *
 * @author kenmi
 * @date 2025-08-28
 */
@RestController
@RequestMapping("/info")
public class SysPositionInfoController extends BaseController {
    @Autowired
    private ISysPositionInfoService sysPositionInfoService;

    /**
     * 查询岗位基础信息列表
     */
    @RequiresPermissions("position:info:list")
    @GetMapping("/list")
    public TableDataInfo list(SysPositionInfo sysPositionInfo) {
        startPage();
        List<SysPositionInfo> list = sysPositionInfoService.selectSysPositionInfoList(sysPositionInfo);
        return getDataTable(list);
    }

    /**
     * 导出岗位基础信息列表
     */
    @RequiresPermissions("position:info:export")
    @Log(title = "岗位基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPositionInfo sysPositionInfo) {
        List<SysPositionInfo> list = sysPositionInfoService.selectSysPositionInfoList(sysPositionInfo);
        ExcelUtil<SysPositionInfo> util = new ExcelUtil<>(SysPositionInfo.class);
        util.exportExcel(response, list, "岗位基础信息数据");
    }

    /**
     * 获取岗位基础信息详细信息
     */
    @RequiresPermissions("position:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysPositionInfoService.selectSysPositionInfoById(id));
    }

    /**
     * 新增岗位基础信息
     */
    @RequiresPermissions("position:info:add")
    @Log(title = "岗位基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPositionInfo sysPositionInfo) {
        return toAjax(sysPositionInfoService.insertSysPositionInfo(sysPositionInfo));
    }

    /**
     * 修改岗位基础信息
     */
    @RequiresPermissions("position:info:edit")
    @Log(title = "岗位基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPositionInfo sysPositionInfo) {
        return toAjax(sysPositionInfoService.updateSysPositionInfo(sysPositionInfo));
    }

    /**
     * 删除岗位基础信息
     */
    @RequiresPermissions("position:info:remove")
    @Log(title = "岗位基础信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysPositionInfoService.deleteSysPositionInfoByIds(ids));
    }
}
