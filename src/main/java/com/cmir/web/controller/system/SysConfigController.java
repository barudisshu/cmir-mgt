/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.web.controller.system;

import com.cmir.common.annotation.Log;
import com.cmir.common.core.controller.BaseController;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.page.TableDataInfo;
import com.cmir.common.enums.BusinessType;
import com.cmir.common.utils.poi.ExcelUtil;
import com.cmir.system.domain.SysConfig;
import com.cmir.system.service.ISysConfigService;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数配置 信息操作处理
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
  @Autowired private ISysConfigService configService;

  /** 获取参数配置列表 */
  @PreAuthorize("@ss.hasPermi('system:config:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysConfig config) {
    startPage();
    List<SysConfig> list = configService.selectConfigList(config);
    return getDataTable(list);
  }

  @Log(title = "参数管理", businessType = BusinessType.EXPORT)
  @PreAuthorize("@ss.hasPermi('system:config:export')")
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysConfig config) {
    List<SysConfig> list = configService.selectConfigList(config);
    ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
    util.exportExcel(response, list, "参数数据");
  }

  /** 根据参数编号获取详细信息 */
  @PreAuthorize("@ss.hasPermi('system:config:query')")
  @GetMapping(value = "/{configId}")
  public AjaxResult getInfo(@PathVariable Long configId) {
    return success(configService.selectConfigById(configId));
  }

  /** 根据参数键名查询参数值 */
  @GetMapping(value = "/configKey/{configKey}")
  public AjaxResult getConfigKey(@PathVariable String configKey) {
    return success(configService.selectConfigByKey(configKey));
  }

  /** 新增参数配置 */
  @PreAuthorize("@ss.hasPermi('system:config:add')")
  @Log(title = "参数管理", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@Validated @RequestBody SysConfig config) {
    if (!configService.checkConfigKeyUnique(config)) {
      return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
    }
    config.setCreateBy(getUsername());
    return toAjax(configService.insertConfig(config));
  }

  /** 修改参数配置 */
  @PreAuthorize("@ss.hasPermi('system:config:edit')")
  @Log(title = "参数管理", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@Validated @RequestBody SysConfig config) {
    if (!configService.checkConfigKeyUnique(config)) {
      return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
    }
    config.setUpdateBy(getUsername());
    return toAjax(configService.updateConfig(config));
  }

  /** 删除参数配置 */
  @PreAuthorize("@ss.hasPermi('system:config:remove')")
  @Log(title = "参数管理", businessType = BusinessType.DELETE)
  @DeleteMapping("/{configIds}")
  public AjaxResult remove(@PathVariable Long[] configIds) {
    configService.deleteConfigByIds(configIds);
    return success();
  }

  /** 刷新参数缓存 */
  @PreAuthorize("@ss.hasPermi('system:config:remove')")
  @Log(title = "参数管理", businessType = BusinessType.CLEAN)
  @DeleteMapping("/refreshCache")
  public AjaxResult refreshCache() {
    configService.resetConfigCache();
    return success();
  }
}
