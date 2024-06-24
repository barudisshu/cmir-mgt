/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.quartz.controller;

import com.cmir.common.annotation.Log;
import com.cmir.common.core.controller.BaseController;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.page.TableDataInfo;
import com.cmir.common.enums.BusinessType;
import com.cmir.common.utils.poi.ExcelUtil;
import com.cmir.quartz.domain.SysJobLog;
import com.cmir.quartz.service.ISysJobLogService;
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
 * 调度日志操作处理
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController {
  @Autowired private ISysJobLogService jobLogService;

  /** 查询定时任务调度日志列表 */
  @PreAuthorize("@ss.hasPermi('monitor:job:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysJobLog sysJobLog) {
    startPage();
    List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
    return getDataTable(list);
  }

  /** 导出定时任务调度日志列表 */
  @PreAuthorize("@ss.hasPermi('monitor:job:export')")
  @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysJobLog sysJobLog) {
    List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
    ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
    util.exportExcel(response, list, "调度日志");
  }

  /** 根据调度编号获取详细信息 */
  @PreAuthorize("@ss.hasPermi('monitor:job:query')")
  @GetMapping(value = "/{jobLogId}")
  public AjaxResult getInfo(@PathVariable Long jobLogId) {
    return success(jobLogService.selectJobLogById(jobLogId));
  }

  /** 删除定时任务调度日志 */
  @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
  @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
  @DeleteMapping("/{jobLogIds}")
  public AjaxResult remove(@PathVariable Long[] jobLogIds) {
    return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
  }

  /** 清空定时任务调度日志 */
  @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
  @Log(title = "调度日志", businessType = BusinessType.CLEAN)
  @DeleteMapping("/clean")
  public AjaxResult clean() {
    jobLogService.cleanJobLog();
    return success();
  }
}
