/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.exception.base;

import com.cmir.common.utils.MessageUtils;
import com.cmir.common.utils.StringUtils;

/**
 * 基础异常
 *
 * @author galudisu
 */
public class BaseException extends RuntimeException {

  /** 所属模块 */
  private final String module;

  /** 错误码 */
  private final String code;

  /** 错误码对应的参数 */
  private final Object[] args;

  /** 错误消息 */
  private final String defaultMessage;

  public BaseException(String module, String code, Object[] args, String defaultMessage) {
    this.module = module;
    this.code = code;
    this.args = args;
    this.defaultMessage = defaultMessage;
  }

  public BaseException(String module, String code, Object[] args) {
    this(module, code, args, null);
  }

  public BaseException(String module, String defaultMessage) {
    this(module, null, null, defaultMessage);
  }

  public BaseException(String code, Object[] args) {
    this(null, code, args, null);
  }

  public BaseException(String defaultMessage) {
    this(null, null, null, defaultMessage);
  }

  @Override
  public String getMessage() {
    String message = null;
    if (!StringUtils.isEmpty(code)) {
      message = MessageUtils.message(code, args);
    }
    if (message == null) {
      message = defaultMessage;
    }
    return message;
  }

  public String getModule() {
    return module;
  }

  public String getCode() {
    return code;
  }

  public Object[] getArgs() {
    return args;
  }

  public String getDefaultMessage() {
    return defaultMessage;
  }
}
