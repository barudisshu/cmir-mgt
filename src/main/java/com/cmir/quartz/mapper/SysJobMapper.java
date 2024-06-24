/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.quartz.mapper;

import com.cmir.quartz.domain.SysJob;
import java.util.List;

/**
 * 调度任务信息 数据层
 *
 * @author galudisu
 */
public interface SysJobMapper {
  /**
   * 查询调度任务日志集合
   *
   * @param job 调度信息
   * @return 操作日志集合
   */
  public List<SysJob> selectJobList(SysJob job);

  /**
   * 查询所有调度任务
   *
   * @return 调度任务列表
   */
  public List<SysJob> selectJobAll();

  /**
   * 通过调度ID查询调度任务信息
   *
   * @param jobId 调度ID
   * @return 角色对象信息
   */
  public SysJob selectJobById(Long jobId);

  /**
   * 通过调度ID删除调度任务信息
   *
   * @param jobId 调度ID
   * @return 结果
   */
  public int deleteJobById(Long jobId);

  /**
   * 批量删除调度任务信息
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  public int deleteJobByIds(Long[] ids);

  /**
   * 修改调度任务信息
   *
   * @param job 调度任务信息
   * @return 结果
   */
  public int updateJob(SysJob job);

  /**
   * 新增调度任务信息
   *
   * @param job 调度任务信息
   * @return 结果
   */
  public int insertJob(SysJob job);
}
