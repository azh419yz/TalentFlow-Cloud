package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.PageQuery;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.bo.TalentCandidateBo;
import com.ruoyi.system.domain.request.TalentCandidateResumeUpdateRequest;
import com.ruoyi.system.domain.vo.TalentCandidateVo;
import com.ruoyi.system.service.ISysTalentCandidateService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public TableDataInfo<TalentCandidateVo> list(TalentCandidateBo talentCandidateBo, PageQuery pageQuery) {
        return sysTalentCandidateService.selectPageTalentCandidateList(talentCandidateBo, pageQuery);
    }

    /**
     * 导出人才库列表
     */
    @RequiresPermissions("system:candidate:export")
    @Log(title = "人才库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TalentCandidateBo talentCandidateBo, HttpServletResponse response) {
        List<TalentCandidateVo> list = sysTalentCandidateService.selectTalentCandidateList(talentCandidateBo);
        ExcelUtil<TalentCandidateVo> util = new ExcelUtil<>(TalentCandidateVo.class);
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
    public AjaxResult add(@RequestBody TalentCandidateBo sysTalentCandidateBo) {
        return toAjax(sysTalentCandidateService.insertSysTalentCandidate(sysTalentCandidateBo));
    }

    /**
     * 修改人才库
     */
    @RequiresPermissions("system:candidate:edit")
    @Log(title = "人才库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TalentCandidateBo sysTalentCandidateBo) {
        return toAjax(sysTalentCandidateService.updateSysTalentCandidate(sysTalentCandidateBo));
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

    /**
     * 更新人才
     */
    @Log(title = "人才库", businessType = BusinessType.UPDATE)
    @PutMapping("/resume")
    public AjaxResult editResume(@Validated @RequestBody TalentCandidateResumeUpdateRequest request) {
        return toAjax(sysTalentCandidateService.updateSysTalentCandidateResume(request));
    }
}
