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
 * 全局异常
 *
 * @author galudisu
 */
public class GlobalException extends RuntimeException {

  /** 错误提示 */
  private String message;

  /**
   * 错误明细，内部调试错误
   *
   * <p>和 {@link CommonResult#getDetailMessage()} 一致的设计
   */
  private String detailMessage;

  /** 空构造方法，避免反序列化问题 */
  public GlobalException() {}

  public GlobalException(String message) {
    this.message = message;
  }

  public String getDetailMessage() {
    return detailMessage;
  }

  public GlobalException setDetailMessage(String detailMessage) {
    this.detailMessage = detailMessage;
    return this;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public GlobalException setMessage(String message) {
    this.message = message;
    return this;
  }
}
