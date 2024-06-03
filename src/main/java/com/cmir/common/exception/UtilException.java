/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception;

/**
 * 工具类异常
 *
 * @author galudisu
 */
public class UtilException extends RuntimeException {

  public UtilException(Throwable e) {
    super(e.getMessage(), e);
  }

  public UtilException(String message) {
    super(message);
  }

  public UtilException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
