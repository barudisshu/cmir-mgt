/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @author galudisu
 */
public class CaptchaException extends UserException {

  public CaptchaException() {
    super("user.jcaptcha.error", null);
  }
}
