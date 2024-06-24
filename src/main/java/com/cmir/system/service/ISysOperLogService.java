/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.service;

import com.cmir.system.domain.SysOperLog;
import java.util.List;

/**
 * 操作日志 服务层
 *
 * @author galudisu
 */
public interface ISysOperLogService {
  /**
   * 新增操作日志
   *
   * @param operLog 操作日志对象
   */
  public void insertOperlog(SysOperLog operLog);

  /**
   * 查询系统操作日志集合
   *
   * @param operLog 操作日志对象
   * @return 操作日志集合
   */
  public List<SysOperLog> selectOperLogList(SysOperLog operLog);

  /**
   * 批量删除系统操作日志
   *
   * @param operIds 需要删除的操作日志ID
   * @return 结果
   */
  public int deleteOperLogByIds(Long[] operIds);

  /**
   * 查询操作日志详细
   *
   * @param operId 操作ID
   * @return 操作日志对象
   */
  public SysOperLog selectOperLogById(Long operId);

  /** 清空操作日志 */
  public void cleanOperLog();
}
