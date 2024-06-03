/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

import com.cmir.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author galudisu
 */
public class UserException extends BaseException {

  public UserException(String code, Object[] args) {
    super("user", code, args, null);
  }
}
