package cc.cmir.web.controller.monitor;

import cc.cmir.common.annotation.Log;
import cc.cmir.common.core.controller.BaseController;
import cc.cmir.common.core.domain.AjaxResult;
import cc.cmir.common.core.page.TableDataInfo;
import cc.cmir.common.enums.BusinessType;
import cc.cmir.common.utils.poi.ExcelUtil;
import cc.cmir.system.domain.SysOperLog;
import cc.cmir.system.service.ISysOperLogService;
import jakarta.servlet.http.HttpServletResponse;
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
 * @author Galudisu
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
