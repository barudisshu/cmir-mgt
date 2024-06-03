/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.user;

/**
 * 验证码失效异常类
 *
 * @author galudisu
 */
public class CaptchaExpireException extends UserException {

  public CaptchaExpireException() {
    super("user.jcaptcha.expire", null);
  }
}
