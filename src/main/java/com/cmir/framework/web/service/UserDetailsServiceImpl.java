/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.web.service;

import com.cmir.common.core.domain.entity.SysUser;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.enums.UserStatus;
import com.cmir.common.exception.ServiceException;
import com.cmir.common.utils.MessageUtils;
import com.cmir.common.utils.StringUtils;
import com.cmir.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author galudisu
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Autowired private ISysUserService userService;

  @Autowired private SysPasswordService passwordService;

  @Autowired private SysPermissionService permissionService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser user = userService.selectUserByUserName(username);
    if (StringUtils.isNull(user)) {
      log.info("登录用户：{} 不存在.", username);
      throw new ServiceException(MessageUtils.message("user.not.exists"));
    } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
      log.info("登录用户：{} 已被删除.", username);
      throw new ServiceException(MessageUtils.message("user.password.delete"));
    } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
      log.info("登录用户：{} 已被停用.", username);
      throw new ServiceException(MessageUtils.message("user.blocked"));
    }

    passwordService.validate(user);

    return createLoginUser(user);
  }

  public UserDetails createLoginUser(SysUser user) {
    return new LoginUser(
        user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
  }
}
