package com.ruoyi.position.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.position.domain.SysPositionRequirement;
import com.ruoyi.position.service.ISysPositionRequirementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位要求详情Controller
 *
 * @author kenmi
 * @date 2025-08-28
 */
@RestController
@RequestMapping("/requirement")
public class SysPositionRequirementController extends BaseController {
    @Autowired
    private ISysPositionRequirementService sysPositionRequirementService;

    /**
     * 查询岗位要求详情列表
     */
    @RequiresPermissions("position:requirement:list")
    @GetMapping("/list")
    public TableDataInfo list(SysPositionRequirement sysPositionRequirement) {
        startPage();
        List<SysPositionRequirement> list = sysPositionRequirementService.selectSysPositionRequirementList(sysPositionRequirement);
        return getDataTable(list);
    }

    /**
     * 导出岗位要求详情列表
     */
    @RequiresPermissions("position:requirement:export")
    @Log(title = "岗位要求详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPositionRequirement sysPositionRequirement) {
        List<SysPositionRequirement> list = sysPositionRequirementService.selectSysPositionRequirementList(sysPositionRequirement);
        ExcelUtil<SysPositionRequirement> util = new ExcelUtil<SysPositionRequirement>(SysPositionRequirement.class);
        util.exportExcel(response, list, "岗位要求详情数据");
    }

    /**
     * 获取岗位要求详情详细信息
     */
    @RequiresPermissions("position:requirement:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysPositionRequirementService.selectSysPositionRequirementById(id));
    }

    /**
     * 新增岗位要求详情
     */
    @RequiresPermissions("position:requirement:add")
    @Log(title = "岗位要求详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPositionRequirement sysPositionRequirement) {
        return toAjax(sysPositionRequirementService.insertSysPositionRequirement(sysPositionRequirement));
    }

    /**
     * 修改岗位要求详情
     */
    @RequiresPermissions("position:requirement:edit")
    @Log(title = "岗位要求详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPositionRequirement sysPositionRequirement) {
        return toAjax(sysPositionRequirementService.updateSysPositionRequirement(sysPositionRequirement));
    }

    /**
     * 删除岗位要求详情
     */
    @RequiresPermissions("position:requirement:remove")
    @Log(title = "岗位要求详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysPositionRequirementService.deleteSysPositionRequirementByIds(ids));
    }
}
