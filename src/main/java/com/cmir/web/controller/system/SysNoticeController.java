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
import com.cmir.system.domain.SysNotice;
import com.cmir.system.service.ISysNoticeService;
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
 * 公告 信息操作处理
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {
  @Autowired private ISysNoticeService noticeService;

  /** 获取通知公告列表 */
  @PreAuthorize("@ss.hasPermi('system:notice:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysNotice notice) {
    startPage();
    List<SysNotice> list = noticeService.selectNoticeList(notice);
    return getDataTable(list);
  }

  /** 根据通知公告编号获取详细信息 */
  @PreAuthorize("@ss.hasPermi('system:notice:query')")
  @GetMapping(value = "/{noticeId}")
  public AjaxResult getInfo(@PathVariable Long noticeId) {
    return success(noticeService.selectNoticeById(noticeId));
  }

  /** 新增通知公告 */
  @PreAuthorize("@ss.hasPermi('system:notice:add')")
  @Log(title = "通知公告", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@Validated @RequestBody SysNotice notice) {
    notice.setCreateBy(getUsername());
    return toAjax(noticeService.insertNotice(notice));
  }

  /** 修改通知公告 */
  @PreAuthorize("@ss.hasPermi('system:notice:edit')")
  @Log(title = "通知公告", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
    notice.setUpdateBy(getUsername());
    return toAjax(noticeService.updateNotice(notice));
  }

  /** 删除通知公告 */
  @PreAuthorize("@ss.hasPermi('system:notice:remove')")
  @Log(title = "通知公告", businessType = BusinessType.DELETE)
  @DeleteMapping("/{noticeIds}")
  public AjaxResult remove(@PathVariable Long[] noticeIds) {
    return toAjax(noticeService.deleteNoticeByIds(noticeIds));
  }
}
