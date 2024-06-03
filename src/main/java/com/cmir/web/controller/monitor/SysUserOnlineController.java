/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.web.controller.monitor;

import com.cmir.common.annotation.Log;
import com.cmir.common.constant.CacheConstants;
import com.cmir.common.core.controller.BaseController;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.core.page.TableDataInfo;
import com.cmir.common.core.redis.RedisCache;
import com.cmir.common.enums.BusinessType;
import com.cmir.common.utils.StringUtils;
import com.cmir.system.domain.SysUserOnline;
import com.cmir.system.service.ISysUserOnlineService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在线用户监控
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends BaseController {
  @Autowired private ISysUserOnlineService userOnlineService;

  @Autowired private RedisCache redisCache;

  @PreAuthorize("@ss.hasPermi('monitor:online:list')")
  @GetMapping("/list")
  public TableDataInfo list(String ipaddr, String userName) {
    Collection<String> keys = redisCache.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
    List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
    for (String key : keys) {
      LoginUser user = redisCache.getCacheObject(key);
      if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
        userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
      } else if (StringUtils.isNotEmpty(ipaddr)) {
        userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
      } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser())) {
        userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
      } else {
        userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
      }
    }
    Collections.reverse(userOnlineList);
    userOnlineList.removeAll(Collections.singleton(null));
    return getDataTable(userOnlineList);
  }

  /** 强退用户 */
  @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
  @Log(title = "在线用户", businessType = BusinessType.FORCE)
  @DeleteMapping("/{tokenId}")
  public AjaxResult forceLogout(@PathVariable String tokenId) {
    redisCache.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + tokenId);
    return success();
  }
}
