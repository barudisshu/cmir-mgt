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

import com.cmir.system.domain.SysLogininfor;
import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author galudisu
 */
public interface SysLogininforMapper {
  /**
   * 新增系统登录日志
   *
   * @param logininfor 访问日志对象
   */
  public void insertLogininfor(SysLogininfor logininfor);

  /**
   * 查询系统登录日志集合
   *
   * @param logininfor 访问日志对象
   * @return 登录记录集合
   */
  public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

  /**
   * 批量删除系统登录日志
   *
   * @param infoIds 需要删除的登录日志ID
   * @return 结果
   */
  public int deleteLogininforByIds(Long[] infoIds);

  /**
   * 清空系统登录日志
   *
   * @return 结果
   */
  public int cleanLogininfor();
}
