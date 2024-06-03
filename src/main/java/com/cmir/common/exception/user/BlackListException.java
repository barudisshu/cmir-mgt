/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 黑名单IP异常类
 *
 * @author galudisu
 */
public class BlackListException extends UserException {

  public BlackListException() {
    super("login.blocked", null);
  }
}
