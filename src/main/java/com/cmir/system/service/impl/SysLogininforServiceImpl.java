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

import com.cmir.system.domain.SysLogininfor;
import com.cmir.system.mapper.SysLogininforMapper;
import com.cmir.system.service.ISysLogininforService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author galudisu
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService {

  @Autowired private SysLogininforMapper logininforMapper;

  /**
   * 新增系统登录日志
   *
   * @param logininfor 访问日志对象
   */
  @Override
  public void insertLogininfor(SysLogininfor logininfor) {
    logininforMapper.insertLogininfor(logininfor);
  }

  /**
   * 查询系统登录日志集合
   *
   * @param logininfor 访问日志对象
   * @return 登录记录集合
   */
  @Override
  public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
    return logininforMapper.selectLogininforList(logininfor);
  }

  /**
   * 批量删除系统登录日志
   *
   * @param infoIds 需要删除的登录日志ID
   * @return 结果
   */
  @Override
  public int deleteLogininforByIds(Long[] infoIds) {
    return logininforMapper.deleteLogininforByIds(infoIds);
  }

  /** 清空系统登录日志 */
  @Override
  public void cleanLogininfor() {
    logininforMapper.cleanLogininfor();
  }
}
