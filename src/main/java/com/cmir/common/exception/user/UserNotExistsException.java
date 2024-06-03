/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 用户不存在异常类
 *
 * @author galudisu
 */
public class UserNotExistsException extends UserException {

  public UserNotExistsException() {
    super("user.not.exists", null);
  }
}
