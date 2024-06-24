/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
