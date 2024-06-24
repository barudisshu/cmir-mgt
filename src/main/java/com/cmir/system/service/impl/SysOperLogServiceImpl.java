/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.service.impl;

import com.cmir.system.domain.SysOperLog;
import com.cmir.system.mapper.SysOperLogMapper;
import com.cmir.system.service.ISysOperLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志 服务层处理
 *
 * @author galudisu
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
  @Autowired private SysOperLogMapper operLogMapper;

  /**
   * 新增操作日志
   *
   * @param operLog 操作日志对象
   */
  @Override
  public void insertOperlog(SysOperLog operLog) {
    operLogMapper.insertOperlog(operLog);
  }

  /**
   * 查询系统操作日志集合
   *
   * @param operLog 操作日志对象
   * @return 操作日志集合
   */
  @Override
  public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
    return operLogMapper.selectOperLogList(operLog);
  }

  /**
   * 批量删除系统操作日志
   *
   * @param operIds 需要删除的操作日志ID
   * @return 结果
   */
  @Override
  public int deleteOperLogByIds(Long[] operIds) {
    return operLogMapper.deleteOperLogByIds(operIds);
  }

  /**
   * 查询操作日志详细
   *
   * @param operId 操作ID
   * @return 操作日志对象
   */
  @Override
  public SysOperLog selectOperLogById(Long operId) {
    return operLogMapper.selectOperLogById(operId);
  }

  /** 清空操作日志 */
  @Override
  public void cleanOperLog() {
    operLogMapper.cleanOperLog();
  }
}
