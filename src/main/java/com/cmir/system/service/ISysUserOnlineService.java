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

import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.system.domain.SysUserOnline;

/**
 * 在线用户 服务层
 *
 * @author galudisu
 */
public interface ISysUserOnlineService {
  /**
   * 通过登录地址查询信息
   *
   * @param ipaddr 登录地址
   * @param user 用户信息
   * @return 在线用户信息
   */
  public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user);

  /**
   * 通过用户名称查询信息
   *
   * @param userName 用户名称
   * @param user 用户信息
   * @return 在线用户信息
   */
  public SysUserOnline selectOnlineByUserName(String userName, LoginUser user);

  /**
   * 通过登录地址/用户名称查询信息
   *
   * @param ipaddr 登录地址
   * @param userName 用户名称
   * @param user 用户信息
   * @return 在线用户信息
   */
  public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user);

  /**
   * 设置在线用户信息
   *
   * @param user 用户信息
   * @return 在线用户
   */
  public SysUserOnline loginUserToUserOnline(LoginUser user);
}
