/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 用户错误最大次数异常类
 *
 * @author galudisu
 */
public class UserPasswordRetryLimitExceedException extends UserException {

  public UserPasswordRetryLimitExceedException(int retryLimitCount, int lockTime) {
    super("user.password.retry.limit.exceed", new Object[] {retryLimitCount, lockTime});
  }
}
