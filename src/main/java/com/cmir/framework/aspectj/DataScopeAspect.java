/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.aspectj;

import com.cmir.common.annotation.DataScope;
import com.cmir.common.core.domain.BaseEntity;
import com.cmir.common.core.domain.entity.SysRole;
import com.cmir.common.core.domain.entity.SysUser;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.core.text.Convert;
import com.cmir.common.utils.SecurityUtils;
import com.cmir.common.utils.StringUtils;
import com.cmir.framework.security.context.PermissionContextHolder;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 数据过滤处理
 *
 * @author galudisu
 */
@Aspect
@Component
public class DataScopeAspect {
  /** 全部数据权限 */
  public static final String DATA_SCOPE_ALL = "1";

  /** 自定数据权限 */
  public static final String DATA_SCOPE_CUSTOM = "2";

  /** 部门数据权限 */
  public static final String DATA_SCOPE_DEPT = "3";

  /** 部门及以下数据权限 */
  public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

  /** 仅本人数据权限 */
  public static final String DATA_SCOPE_SELF = "5";

  /** 数据权限过滤关键字 */
  public static final String DATA_SCOPE = "dataScope";

  @Before("@annotation(controllerDataScope)")
  public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable {
    clearDataScope(point);
    handleDataScope(point, controllerDataScope);
  }

  protected void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope) {
    // 获取当前的用户
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (StringUtils.isNotNull(loginUser)) {
      SysUser currentUser = loginUser.getUser();
      // 如果是超级管理员，则不过滤数据
      if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
        String permission =
            StringUtils.defaultIfEmpty(
                controllerDataScope.permission(), PermissionContextHolder.getContext());
        dataScopeFilter(
            joinPoint,
            currentUser,
            controllerDataScope.deptAlias(),
            controllerDataScope.userAlias(),
            permission);
      }
    }
  }

  /**
   * 数据范围过滤
   *
   * @param joinPoint 切点
   * @param user 用户
   * @param deptAlias 部门别名
   * @param userAlias 用户别名
   * @param permission 权限字符
   */
  public static void dataScopeFilter(
      JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias, String permission) {
    StringBuilder sqlString = new StringBuilder();
    List<String> conditions = new ArrayList<String>();

    for (SysRole role : user.getRoles()) {
      String dataScope = role.getDataScope();
      if (!DATA_SCOPE_CUSTOM.equals(dataScope) && conditions.contains(dataScope)) {
        continue;
      }
      if (StringUtils.isNotEmpty(permission)
          && StringUtils.isNotEmpty(role.getPermissions())
          && !StringUtils.containsAny(role.getPermissions(), Convert.toStrArray(permission))) {
        continue;
      }
      if (DATA_SCOPE_ALL.equals(dataScope)) {
        sqlString = new StringBuilder();
        conditions.add(dataScope);
        break;
      } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
        sqlString.append(
            StringUtils.format(
                " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ",
                deptAlias,
                role.getRoleId()));
      } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
        sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, user.getDeptId()));
      } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
        sqlString.append(
            StringUtils.format(
                " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
                deptAlias,
                user.getDeptId(),
                user.getDeptId()));
      } else if (DATA_SCOPE_SELF.equals(dataScope)) {
        if (StringUtils.isNotBlank(userAlias)) {
          sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
        } else {
          // 数据权限为仅本人且没有userAlias别名不查询任何数据
          sqlString.append(StringUtils.format(" OR {}.dept_id = 0 ", deptAlias));
        }
      }
      conditions.add(dataScope);
    }

    // 多角色情况下，所有角色都不包含传递过来的权限字符，这个时候sqlString也会为空，所以要限制一下,不查询任何数据
    if (StringUtils.isEmpty(conditions)) {
      sqlString.append(StringUtils.format(" OR {}.dept_id = 0 ", deptAlias));
    }

    if (StringUtils.isNotBlank(sqlString.toString())) {
      Object params = joinPoint.getArgs()[0];
      if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
        BaseEntity baseEntity = (BaseEntity) params;
        baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
      }
    }
  }

  /** 拼接权限sql前先清空params.dataScope参数防止注入 */
  private void clearDataScope(final JoinPoint joinPoint) {
    Object params = joinPoint.getArgs()[0];
    if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
      BaseEntity baseEntity = (BaseEntity) params;
      baseEntity.getParams().put(DATA_SCOPE, "");
    }
  }
}
