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
import com.cmir.common.core.domain.entity.SysDictData;
import com.cmir.common.core.page.TableDataInfo;
import com.cmir.common.enums.BusinessType;
import com.cmir.common.utils.StringUtils;
import com.cmir.common.utils.poi.ExcelUtil;
import com.cmir.system.service.ISysDictDataService;
import com.cmir.system.service.ISysDictTypeService;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
 * 数据字典信息
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
  @Autowired private ISysDictDataService dictDataService;

  @Autowired private ISysDictTypeService dictTypeService;

  @PreAuthorize("@ss.hasPermi('system:dict:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysDictData dictData) {
    startPage();
    List<SysDictData> list = dictDataService.selectDictDataList(dictData);
    return getDataTable(list);
  }

  @Log(title = "字典数据", businessType = BusinessType.EXPORT)
  @PreAuthorize("@ss.hasPermi('system:dict:export')")
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysDictData dictData) {
    List<SysDictData> list = dictDataService.selectDictDataList(dictData);
    ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
    util.exportExcel(response, list, "字典数据");
  }

  /** 查询字典数据详细 */
  @PreAuthorize("@ss.hasPermi('system:dict:query')")
  @GetMapping(value = "/{dictCode}")
  public AjaxResult getInfo(@PathVariable Long dictCode) {
    return success(dictDataService.selectDictDataById(dictCode));
  }

  /** 根据字典类型查询字典数据信息 */
  @GetMapping(value = "/type/{dictType}")
  public AjaxResult dictType(@PathVariable String dictType) {
    List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
    if (StringUtils.isNull(data)) {
      data = new ArrayList<SysDictData>();
    }
    return success(data);
  }

  /** 新增字典类型 */
  @PreAuthorize("@ss.hasPermi('system:dict:add')")
  @Log(title = "字典数据", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@Validated @RequestBody SysDictData dict) {
    dict.setCreateBy(getUsername());
    return toAjax(dictDataService.insertDictData(dict));
  }

  /** 修改保存字典类型 */
  @PreAuthorize("@ss.hasPermi('system:dict:edit')")
  @Log(title = "字典数据", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
    dict.setUpdateBy(getUsername());
    return toAjax(dictDataService.updateDictData(dict));
  }

  /** 删除字典类型 */
  @PreAuthorize("@ss.hasPermi('system:dict:remove')")
  @Log(title = "字典类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{dictCodes}")
  public AjaxResult remove(@PathVariable Long[] dictCodes) {
    dictDataService.deleteDictDataByIds(dictCodes);
    return success();
  }
}
