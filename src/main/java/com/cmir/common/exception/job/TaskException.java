/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.exception.job;

/**
 * 计划策略异常
 *
 * @author galudisu
 */
public class TaskException extends Exception {

  private final Code code;

  public TaskException(String msg, Code code) {
    this(msg, code, null);
  }

  public TaskException(String msg, Code code, Exception nestedEx) {
    super(msg, nestedEx);
    this.code = code;
  }

  public Code getCode() {
    return code;
  }

  public enum Code {
    TASK_EXISTS,
    NO_TASK_EXISTS,
    TASK_ALREADY_STARTED,
    UNKNOWN,
    CONFIG_ERROR,
    TASK_NODE_NOT_AVAILABLE
  }
}
