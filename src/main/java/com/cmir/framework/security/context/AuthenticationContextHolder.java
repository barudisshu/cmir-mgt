/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.security.context;

import org.springframework.security.core.Authentication;

/**
 * 身份验证信息
 *
 * @author galudisu
 */
public class AuthenticationContextHolder {
  private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

  public static Authentication getContext() {
    return contextHolder.get();
  }

  public static void setContext(Authentication context) {
    contextHolder.set(context);
  }

  public static void clearContext() {
    contextHolder.remove();
  }
}
