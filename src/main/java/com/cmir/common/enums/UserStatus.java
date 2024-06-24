/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.enums;

/**
 * 用户状态
 *
 * @author galudisu
 */
public enum UserStatus {
  OK("0", "正常"),
  DISABLE("1", "停用"),
  DELETED("2", "删除");

  private final String code;
  private final String info;

  UserStatus(String code, String info) {
    this.code = code;
    this.info = info;
  }

  public String getCode() {
    return code;
  }

  public String getInfo() {
    return info;
  }
}
