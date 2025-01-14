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

import com.cmir.common.core.controller.BaseController;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.domain.model.RegisterBody;
import com.cmir.common.utils.StringUtils;
import com.cmir.framework.web.service.SysRegisterService;
import com.cmir.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册验证
 *
 * @author galudisu
 */
@RestController
public class SysRegisterController extends BaseController {
  @Autowired private SysRegisterService registerService;

  @Autowired private ISysConfigService configService;

  @PostMapping("/register")
  public AjaxResult register(@RequestBody RegisterBody user) {
    if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
      return error("当前系统没有开启注册功能！");
    }
    String msg = registerService.register(user);
    return StringUtils.isEmpty(msg) ? success() : error(msg);
  }
}
