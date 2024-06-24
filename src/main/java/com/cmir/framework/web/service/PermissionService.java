/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.web.service;

import com.cmir.common.constant.Constants;
import com.cmir.common.core.domain.entity.SysRole;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.utils.SecurityUtils;
import com.cmir.common.utils.StringUtils;
import com.cmir.framework.security.context.PermissionContextHolder;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 首创 自定义权限实现，ss取自SpringSecurity首字母
 *
 * @author galudisu
 */
@Service("ss")
public class PermissionService {
  /**
   * 验证用户是否具备某权限
   *
   * @param permission 权限字符串
   * @return 用户是否具备某权限
   */
  public boolean hasPermi(String permission) {
    if (StringUtils.isEmpty(permission)) {
      return false;
    }
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
      return false;
    }
    PermissionContextHolder.setContext(permission);
    return hasPermissions(loginUser.getPermissions(), permission);
  }

  /**
   * 验证用户是否不具备某权限，与 hasPermi逻辑相反
   *
   * @param permission 权限字符串
   * @return 用户是否不具备某权限
   */
  public boolean lacksPermi(String permission) {
    return hasPermi(permission) != true;
  }

  /**
   * 验证用户是否具有以下任意一个权限
   *
   * @param permissions 以 PERMISSION_DELIMETER 为分隔符的权限列表
   * @return 用户是否具有以下任意一个权限
   */
  public boolean hasAnyPermi(String permissions) {
    if (StringUtils.isEmpty(permissions)) {
      return false;
    }
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
      return false;
    }
    PermissionContextHolder.setContext(permissions);
    Set<String> authorities = loginUser.getPermissions();
    for (String permission : permissions.split(Constants.PERMISSION_DELIMETER)) {
      if (permission != null && hasPermissions(authorities, permission)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断用户是否拥有某个角色
   *
   * @param role 角色字符串
   * @return 用户是否具备某角色
   */
  public boolean hasRole(String role) {
    if (StringUtils.isEmpty(role)) {
      return false;
    }
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
      return false;
    }
    for (SysRole sysRole : loginUser.getUser().getRoles()) {
      String roleKey = sysRole.getRoleKey();
      if (Constants.SUPER_ADMIN.equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
        return true;
      }
    }
    return false;
  }

  /**
   * 验证用户是否不具备某角色，与 isRole逻辑相反。
   *
   * @param role 角色名称
   * @return 用户是否不具备某角色
   */
  public boolean lacksRole(String role) {
    return hasRole(role) != true;
  }

  /**
   * 验证用户是否具有以下任意一个角色
   *
   * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
   * @return 用户是否具有以下任意一个角色
   */
  public boolean hasAnyRoles(String roles) {
    if (StringUtils.isEmpty(roles)) {
      return false;
    }
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
      return false;
    }
    for (String role : roles.split(Constants.ROLE_DELIMETER)) {
      if (hasRole(role)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断是否包含权限
   *
   * @param permissions 权限列表
   * @param permission 权限字符串
   * @return 用户是否具备某权限
   */
  private boolean hasPermissions(Set<String> permissions, String permission) {
    return permissions.contains(Constants.ALL_PERMISSION)
        || permissions.contains(StringUtils.trim(permission));
  }
}
