package cc.cmir.web.controller.monitor;

import cc.cmir.common.annotation.Log;
import cc.cmir.common.core.controller.BaseController;
import cc.cmir.common.core.domain.AjaxResult;
import cc.cmir.common.core.page.TableDataInfo;
import cc.cmir.common.enums.BusinessType;
import cc.cmir.common.utils.poi.ExcelUtil;
import cc.cmir.framework.web.service.SysPasswordService;
import cc.cmir.system.domain.SysLogininfor;
import cc.cmir.system.service.ISysLogininforService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统访问记录
 *
 * @author Galudisu
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {
  private final ISysLogininforService logininforService;

  private final SysPasswordService passwordService;

  public SysLogininforController(
      ISysLogininforService logininforService, SysPasswordService passwordService) {
    this.logininforService = logininforService;
    this.passwordService = passwordService;
  }

  @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysLogininfor logininfor) {
    startPage();
    List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
    return getDataTable(list);
  }

  @Log(title = "登录日志", businessType = BusinessType.EXPORT)
  @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysLogininfor logininfor) {
    List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
    ExcelUtil<SysLogininfor> util = new ExcelUtil<>(SysLogininfor.class);
    util.exportExcel(response, list, "登录日志");
  }

  @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
  @Log(title = "登录日志", businessType = BusinessType.DELETE)
  @DeleteMapping("/{infoIds}")
  public AjaxResult remove(@PathVariable Long[] infoIds) {
    return toAjax(logininforService.deleteLogininforByIds(infoIds));
  }

  @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
  @Log(title = "登录日志", businessType = BusinessType.CLEAN)
  @DeleteMapping("/clean")
  public AjaxResult clean() {
    logininforService.cleanLogininfor();
    return success();
  }

  @PreAuthorize("@ss.hasPermi('monitor:logininfor:unlock')")
  @Log(title = "账户解锁", businessType = BusinessType.OTHER)
  @GetMapping("/unlock/{userName}")
  public AjaxResult unlock(@PathVariable("userName") String userName) {
    passwordService.clearLoginRecordCache(userName);
    return success();
  }
}
