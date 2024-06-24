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

import com.cmir.common.core.text.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 权限信息
 *
 * @author galudisu
 */
public class PermissionContextHolder {
  private static final String PERMISSION_CONTEXT_ATTRIBUTES = "PERMISSION_CONTEXT";

  public static void setContext(String permission) {
    RequestContextHolder.currentRequestAttributes()
        .setAttribute(PERMISSION_CONTEXT_ATTRIBUTES, permission, RequestAttributes.SCOPE_REQUEST);
  }

  public static String getContext() {
    return Convert.toStr(
        RequestContextHolder.currentRequestAttributes()
            .getAttribute(PERMISSION_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST));
  }
}
