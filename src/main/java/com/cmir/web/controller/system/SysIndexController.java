/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.web.controller.system;

import com.cmir.common.config.properties.CmirConfig;
import com.cmir.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author galudisu
 */
@RestController
public class SysIndexController {
  /** 系统基础配置 */
  @Autowired private CmirConfig cmirConfig;

  /** 访问首页，提示语 */
  @RequestMapping("/")
  public String index() {
    return StringUtils.format(
        "欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cmirConfig.getName(), cmirConfig.getVersion());
  }
}
