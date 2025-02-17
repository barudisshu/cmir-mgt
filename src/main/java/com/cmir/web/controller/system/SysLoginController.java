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

import com.cmir.common.constant.Constants;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.domain.entity.SysMenu;
import com.cmir.common.core.domain.entity.SysUser;
import com.cmir.common.core.domain.model.LoginBody;
import com.cmir.common.utils.SecurityUtils;
import com.cmir.framework.web.service.SysLoginService;
import com.cmir.framework.web.service.SysPermissionService;
import com.cmir.system.service.ISysMenuService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * @author galudisu
 */
@RestController
public class SysLoginController {
  @Autowired private SysLoginService loginService;

  @Autowired private ISysMenuService menuService;

  @Autowired private SysPermissionService permissionService;

  /**
   * 登录方法
   *
   * @param loginBody 登录信息
   * @return 结果
   */
  @PostMapping("/login")
  public AjaxResult login(@RequestBody LoginBody loginBody) {
    AjaxResult ajax = AjaxResult.success();
    // 生成令牌
    String token =
        loginService.login(
            loginBody.getUsername(),
            loginBody.getPassword(),
            loginBody.getCode(),
            loginBody.getUuid());
    ajax.put(Constants.TOKEN, token);
    return ajax;
  }

  /**
   * 获取用户信息
   *
   * @return 用户信息
   */
  @GetMapping("getInfo")
  public AjaxResult getInfo() {
    SysUser user = SecurityUtils.getLoginUser().getUser();
    // 角色集合
    Set<String> roles = permissionService.getRolePermission(user);
    // 权限集合
    Set<String> permissions = permissionService.getMenuPermission(user);
    AjaxResult ajax = AjaxResult.success();
    ajax.put("user", user);
    ajax.put("roles", roles);
    ajax.put("permissions", permissions);
    return ajax;
  }

  /**
   * 获取路由信息
   *
   * @return 路由信息
   */
  @GetMapping("getRouters")
  public AjaxResult getRouters() {
    Long userId = SecurityUtils.getUserId();
    List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
    return AjaxResult.success(menuService.buildMenus(menus));
  }
}
