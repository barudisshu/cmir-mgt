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

import com.cmir.system.domain.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户与角色关联表 数据层
 *
 * @author galudisu
 */
public interface SysUserRoleMapper {
  /**
   * 通过用户ID删除用户和角色关联
   *
   * @param userId 用户ID
   * @return 结果
   */
  public int deleteUserRoleByUserId(Long userId);

  /**
   * 批量删除用户和角色关联
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  public int deleteUserRole(Long[] ids);

  /**
   * 通过角色ID查询角色使用数量
   *
   * @param roleId 角色ID
   * @return 结果
   */
  public int countUserRoleByRoleId(Long roleId);

  /**
   * 批量新增用户角色信息
   *
   * @param userRoleList 用户角色列表
   * @return 结果
   */
  public int batchUserRole(List<SysUserRole> userRoleList);

  /**
   * 删除用户和角色关联信息
   *
   * @param userRole 用户和角色关联信息
   * @return 结果
   */
  public int deleteUserRoleInfo(SysUserRole userRole);

  /**
   * 批量取消授权用户角色
   *
   * @param roleId 角色ID
   * @param userIds 需要删除的用户数据ID
   * @return 结果
   */
  public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
