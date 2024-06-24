/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
