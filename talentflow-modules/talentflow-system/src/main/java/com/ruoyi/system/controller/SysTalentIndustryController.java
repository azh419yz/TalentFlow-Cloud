package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysTalentIndustry;
import com.ruoyi.system.service.ISysTalentIndustryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人才行业分类Controller
 *
 * @author kenmi
 * @date 2025-08-21
 */
@RestController
@RequestMapping("/talent/industry")
public class SysTalentIndustryController extends BaseController {
    @Autowired
    private ISysTalentIndustryService sysTalentIndustryService;

    /**
     * 查询人才职位分类列表
     */
    @RequiresPermissions("system:post:list")
    @GetMapping("/all")
    public AjaxResult all() {
        return success(sysTalentIndustryService.selectAllIndustries());
    }

    /**
     * 查询人才行业分类列表
     */
    @RequiresPermissions("system:industry:list")
    @GetMapping("/list")
    public TableDataInfo list(SysTalentIndustry sysTalentIndustry) {
        startPage();
        List<SysTalentIndustry> list = sysTalentIndustryService.selectSysTalentIndustryList(sysTalentIndustry);
        return getDataTable(list);
    }

    /**
     * 导出人才行业分类列表
     */
    @RequiresPermissions("system:industry:export")
    @Log(title = "人才行业分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTalentIndustry sysTalentIndustry) {
        List<SysTalentIndustry> list = sysTalentIndustryService.selectSysTalentIndustryList(sysTalentIndustry);
        ExcelUtil<SysTalentIndustry> util = new ExcelUtil<SysTalentIndustry>(SysTalentIndustry.class);
        util.exportExcel(response, list, "人才行业分类数据");
    }

    /**
     * 获取人才行业分类详细信息
     */
    @RequiresPermissions("system:industry:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysTalentIndustryService.selectSysTalentIndustryById(id));
    }

    /**
     * 新增人才行业分类
     */
    @RequiresPermissions("system:industry:add")
    @Log(title = "人才行业分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTalentIndustry sysTalentIndustry) {
        return toAjax(sysTalentIndustryService.insertSysTalentIndustry(sysTalentIndustry));
    }

    /**
     * 修改人才行业分类
     */
    @RequiresPermissions("system:industry:edit")
    @Log(title = "人才行业分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTalentIndustry sysTalentIndustry) {
        return toAjax(sysTalentIndustryService.updateSysTalentIndustry(sysTalentIndustry));
    }

    /**
     * 删除人才行业分类
     */
    @RequiresPermissions("system:industry:remove")
    @Log(title = "人才行业分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysTalentIndustryService.deleteSysTalentIndustryByIds(ids));
    }
}
