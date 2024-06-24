/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 错误信息处理类。
 *
 * @author galudisu
 */
public class ExceptionUtil {
  /** 获取exception的详细错误信息。 */
  public static String getExceptionMessage(Throwable e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw, true));
    return sw.toString();
  }

  public static String getRootErrorMessage(Exception e) {
    Throwable root = ExceptionUtils.getRootCause(e);
    root = (root == null ? e : root);
    if (root == null) {
      return "";
    }
    String msg = root.getMessage();
    if (msg == null) {
      return "null";
    }
    return StringUtils.defaultString(msg);
  }
}
