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

import com.cmir.system.domain.SysUserPost;
import java.util.List;

/**
 * 用户与岗位关联表 数据层
 *
 * @author galudisu
 */
public interface SysUserPostMapper {
  /**
   * 通过用户ID删除用户和岗位关联
   *
   * @param userId 用户ID
   * @return 结果
   */
  public int deleteUserPostByUserId(Long userId);

  /**
   * 通过岗位ID查询岗位使用数量
   *
   * @param postId 岗位ID
   * @return 结果
   */
  public int countUserPostById(Long postId);

  /**
   * 批量删除用户和岗位关联
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  public int deleteUserPost(Long[] ids);

  /**
   * 批量新增用户岗位信息
   *
   * @param userPostList 用户岗位列表
   * @return 结果
   */
  public int batchUserPost(List<SysUserPost> userPostList);
}
