/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.mapper;

import com.cmir.system.domain.SysRoleMenu;
import java.util.List;

/**
 * 角色与菜单关联表 数据层
 *
 * @author galudisu
 */
public interface SysRoleMenuMapper {
  /**
   * 查询菜单使用数量
   *
   * @param menuId 菜单ID
   * @return 结果
   */
  public int checkMenuExistRole(Long menuId);

  /**
   * 通过角色ID删除角色和菜单关联
   *
   * @param roleId 角色ID
   * @return 结果
   */
  public int deleteRoleMenuByRoleId(Long roleId);

  /**
   * 批量删除角色菜单关联信息
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  public int deleteRoleMenu(Long[] ids);

  /**
   * 批量新增角色菜单信息
   *
   * @param roleMenuList 角色菜单列表
   * @return 结果
   */
  public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
