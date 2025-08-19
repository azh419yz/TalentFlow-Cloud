package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysTalentPost;
import com.ruoyi.system.service.ISysTalentPostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人才职位分类Controller
 *
 * @author kenmi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/talent/post")
public class SysTalentPostController extends BaseController {
    @Autowired
    private ISysTalentPostService sysTalentPostService;

    /**
     * 查询人才职位分类列表
     */
    @RequiresPermissions("system:post:list")
    @GetMapping("/all")
    public AjaxResult all() {
        return success(sysTalentPostService.selectAllPosts());
    }

    /**
     * 查询人才职位分类列表
     */
    @RequiresPermissions("system:post:list")
    @GetMapping("/list")
    public TableDataInfo list(SysTalentPost sysTalentPost) {
        startPage();
        List<SysTalentPost> list = sysTalentPostService.selectSysTalentPostList(sysTalentPost);
        return getDataTable(list);
    }

    /**
     * 导出人才职位分类列表
     */
    @RequiresPermissions("system:post:export")
    @Log(title = "人才职位分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTalentPost sysTalentPost) {
        List<SysTalentPost> list = sysTalentPostService.selectSysTalentPostList(sysTalentPost);
        ExcelUtil<SysTalentPost> util = new ExcelUtil<SysTalentPost>(SysTalentPost.class);
        util.exportExcel(response, list, "人才职位分类数据");
    }

    /**
     * 获取人才职位分类详细信息
     */
    @RequiresPermissions("system:post:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysTalentPostService.selectSysTalentPostById(id));
    }

    /**
     * 新增人才职位分类
     */
    @RequiresPermissions("system:post:add")
    @Log(title = "人才职位分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTalentPost sysTalentPost) {
        return toAjax(sysTalentPostService.insertSysTalentPost(sysTalentPost));
    }

    /**
     * 修改人才职位分类
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "人才职位分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTalentPost sysTalentPost) {
        return toAjax(sysTalentPostService.updateSysTalentPost(sysTalentPost));
    }

    /**
     * 删除人才职位分类
     */
    @RequiresPermissions("system:post:remove")
    @Log(title = "人才职位分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysTalentPostService.deleteSysTalentPostByIds(ids));
    }
}
