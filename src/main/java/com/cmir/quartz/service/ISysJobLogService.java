/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.quartz.service;

import com.cmir.quartz.domain.SysJobLog;
import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 *
 * @author galudisu
 */
public interface ISysJobLogService {
  /**
   * 获取quartz调度器日志的计划任务
   *
   * @param jobLog 调度日志信息
   * @return 调度任务日志集合
   */
  public List<SysJobLog> selectJobLogList(SysJobLog jobLog);

  /**
   * 通过调度任务日志ID查询调度信息
   *
   * @param jobLogId 调度任务日志ID
   * @return 调度任务日志对象信息
   */
  public SysJobLog selectJobLogById(Long jobLogId);

  /**
   * 新增任务日志
   *
   * @param jobLog 调度日志信息
   */
  public void addJobLog(SysJobLog jobLog);

  /**
   * 批量删除调度日志信息
   *
   * @param logIds 需要删除的日志ID
   * @return 结果
   */
  public int deleteJobLogByIds(Long[] logIds);

  /**
   * 删除任务日志
   *
   * @param jobId 调度日志ID
   * @return 结果
   */
  public int deleteJobLogById(Long jobId);

  /** 清空任务日志 */
  public void cleanJobLog();
}
