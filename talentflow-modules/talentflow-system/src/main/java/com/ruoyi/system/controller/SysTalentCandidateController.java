package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysTalentCandidate;
import com.ruoyi.system.service.ISysTalentCandidateService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人才库Controller
 *
 * @author kenmi
 * @date 2025-08-15
 */
@RestController
@RequestMapping("/talent/candidate")
public class SysTalentCandidateController extends BaseController {
    @Autowired
    private ISysTalentCandidateService sysTalentCandidateService;

    /**
     * 查询人才库列表
     */
    @RequiresPermissions("system:candidate:list")
    @GetMapping("/list")
    public TableDataInfo list(SysTalentCandidate sysTalentCandidate) {
        startPage();
        List<SysTalentCandidate> list = sysTalentCandidateService.selectSysTalentCandidateList(sysTalentCandidate);
        return getDataTable(list);
    }

    /**
     * 导出人才库列表
     */
    @RequiresPermissions("system:candidate:export")
    @Log(title = "人才库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTalentCandidate sysTalentCandidate) {
        List<SysTalentCandidate> list = sysTalentCandidateService.selectSysTalentCandidateList(sysTalentCandidate);
        ExcelUtil<SysTalentCandidate> util = new ExcelUtil<SysTalentCandidate>(SysTalentCandidate.class);
        util.exportExcel(response, list, "人才库数据");
    }

    /**
     * 获取人才库详细信息
     */
    @RequiresPermissions("system:candidate:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysTalentCandidateService.selectSysTalentCandidateById(id));
    }

    /**
     * 新增人才库
     */
    @RequiresPermissions("system:candidate:add")
    @Log(title = "人才库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTalentCandidate sysTalentCandidate) {
        return toAjax(sysTalentCandidateService.insertSysTalentCandidate(sysTalentCandidate));
    }

    /**
     * 修改人才库
     */
    @RequiresPermissions("system:candidate:edit")
    @Log(title = "人才库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTalentCandidate sysTalentCandidate) {
        return toAjax(sysTalentCandidateService.updateSysTalentCandidate(sysTalentCandidate));
    }

    /**
     * 删除人才库
     */
    @RequiresPermissions("system:candidate:remove")
    @Log(title = "人才库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysTalentCandidateService.deleteSysTalentCandidateByIds(ids));
    }
}
