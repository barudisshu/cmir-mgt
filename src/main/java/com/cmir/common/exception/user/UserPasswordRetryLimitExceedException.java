/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
