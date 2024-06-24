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

import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.Nullable;

/**
 * 请求方式
 *
 * @author galudisu
 */
public enum HttpMethod {
  GET,
  HEAD,
  POST,
  PUT,
  PATCH,
  DELETE,
  OPTIONS,
  TRACE;

  private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

  static {
    for (HttpMethod httpMethod : values()) {
      mappings.put(httpMethod.name(), httpMethod);
    }
  }

  @Nullable
  public static HttpMethod resolve(@Nullable String method) {
    return (method != null ? mappings.get(method) : null);
  }

  public boolean matches(String method) {
    return (this == resolve(method));
  }
}
