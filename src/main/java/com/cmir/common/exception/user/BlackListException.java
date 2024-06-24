/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
