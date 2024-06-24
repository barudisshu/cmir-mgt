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

import com.cmir.common.constant.CacheConstants;
import com.cmir.common.core.domain.entity.SysUser;
import com.cmir.common.core.redis.RedisCache;
import com.cmir.common.exception.user.UserPasswordNotMatchException;
import com.cmir.common.exception.user.UserPasswordRetryLimitExceedException;
import com.cmir.common.utils.SecurityUtils;
import com.cmir.framework.security.context.AuthenticationContextHolder;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 登录密码方法
 *
 * @author galudisu
 */
@Component
public class SysPasswordService {
  @Autowired private RedisCache redisCache;

  @Value(value = "${user.password.maxRetryCount}")
  private int maxRetryCount;

  @Value(value = "${user.password.lockTime}")
  private int lockTime;

  /**
   * 登录账户密码错误次数缓存键名
   *
   * @param username 用户名
   * @return 缓存键key
   */
  private String getCacheKey(String username) {
    return CacheConstants.PWD_ERR_CNT_KEY + username;
  }

  public void validate(SysUser user) {
    Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
    String username = usernamePasswordAuthenticationToken.getName();
    String password = usernamePasswordAuthenticationToken.getCredentials().toString();

    Integer retryCount = redisCache.getCacheObject(getCacheKey(username));

    if (retryCount == null) {
      retryCount = 0;
    }

    if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
      throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
    }

    if (!matches(user, password)) {
      retryCount = retryCount + 1;
      redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
      throw new UserPasswordNotMatchException();
    } else {
      clearLoginRecordCache(username);
    }
  }

  public boolean matches(SysUser user, String rawPassword) {
    return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
  }

  public void clearLoginRecordCache(String loginName) {
    if (redisCache.hasKey(getCacheKey(loginName))) {
      redisCache.deleteObject(getCacheKey(loginName));
    }
  }
}
