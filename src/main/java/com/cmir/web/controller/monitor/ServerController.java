/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.web.controller.monitor;

import com.cmir.common.core.domain.AjaxResult;
import com.cmir.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author galudisu
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
  @PreAuthorize("@ss.hasPermi('monitor:server:list')")
  @GetMapping()
  public AjaxResult getInfo() throws Exception {
    Server server = new Server();
    server.copyTo();
    return AjaxResult.success(server);
  }
}
