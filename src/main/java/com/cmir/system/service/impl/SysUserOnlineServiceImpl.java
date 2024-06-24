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

import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.utils.StringUtils;
import com.cmir.system.domain.SysUserOnline;
import com.cmir.system.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

/**
 * 在线用户 服务层处理
 *
 * @author galudisu
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService {
  /**
   * 通过登录地址查询信息
   *
   * @param ipaddr 登录地址
   * @param user 用户信息
   * @return 在线用户信息
   */
  @Override
  public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
    if (StringUtils.equals(ipaddr, user.getIpaddr())) {
      return loginUserToUserOnline(user);
    }
    return null;
  }

  /**
   * 通过用户名称查询信息
   *
   * @param userName 用户名称
   * @param user 用户信息
   * @return 在线用户信息
   */
  @Override
  public SysUserOnline selectOnlineByUserName(String userName, LoginUser user) {
    if (StringUtils.equals(userName, user.getUsername())) {
      return loginUserToUserOnline(user);
    }
    return null;
  }

  /**
   * 通过登录地址/用户名称查询信息
   *
   * @param ipaddr 登录地址
   * @param userName 用户名称
   * @param user 用户信息
   * @return 在线用户信息
   */
  @Override
  public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
    if (StringUtils.equals(ipaddr, user.getIpaddr())
        && StringUtils.equals(userName, user.getUsername())) {
      return loginUserToUserOnline(user);
    }
    return null;
  }

  /**
   * 设置在线用户信息
   *
   * @param user 用户信息
   * @return 在线用户
   */
  @Override
  public SysUserOnline loginUserToUserOnline(LoginUser user) {
    if (StringUtils.isNull(user) || StringUtils.isNull(user.getUser())) {
      return null;
    }
    SysUserOnline sysUserOnline = new SysUserOnline();
    sysUserOnline.setTokenId(user.getToken());
    sysUserOnline.setUserName(user.getUsername());
    sysUserOnline.setIpaddr(user.getIpaddr());
    sysUserOnline.setLoginLocation(user.getLoginLocation());
    sysUserOnline.setBrowser(user.getBrowser());
    sysUserOnline.setOs(user.getOs());
    sysUserOnline.setLoginTime(user.getLoginTime());
    if (StringUtils.isNotNull(user.getUser().getDept())) {
      sysUserOnline.setDeptName(user.getUser().getDept().getDeptName());
    }
    return sysUserOnline;
  }
}
