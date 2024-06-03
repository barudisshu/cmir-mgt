/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 用户密码不正确或不符合规范异常类
 *
 * @author galudisu
 */
public class UserPasswordNotMatchException extends UserException {

  public UserPasswordNotMatchException() {
    super("user.password.not.match", null);
  }
}
