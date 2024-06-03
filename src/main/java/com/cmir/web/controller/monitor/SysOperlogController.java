/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.web.controller.monitor;

import com.cmir.common.annotation.Log;
import com.cmir.common.core.controller.BaseController;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.page.TableDataInfo;
import com.cmir.common.enums.BusinessType;
import com.cmir.common.utils.poi.ExcelUtil;
import com.cmir.system.domain.SysOperLog;
import com.cmir.system.service.ISysOperLogService;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志记录
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController {
  @Autowired private ISysOperLogService operLogService;

  @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysOperLog operLog) {
    startPage();
    List<SysOperLog> list = operLogService.selectOperLogList(operLog);
    return getDataTable(list);
  }

  @Log(title = "操作日志", businessType = BusinessType.EXPORT)
  @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysOperLog operLog) {
    List<SysOperLog> list = operLogService.selectOperLogList(operLog);
    ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
    util.exportExcel(response, list, "操作日志");
  }

  @Log(title = "操作日志", businessType = BusinessType.DELETE)
  @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
  @DeleteMapping("/{operIds}")
  public AjaxResult remove(@PathVariable Long[] operIds) {
    return toAjax(operLogService.deleteOperLogByIds(operIds));
  }

  @Log(title = "操作日志", businessType = BusinessType.CLEAN)
  @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
  @DeleteMapping("/clean")
  public AjaxResult clean() {
    operLogService.cleanOperLog();
    return success();
  }
}
