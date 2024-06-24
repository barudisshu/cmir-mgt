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

/**
 * 处理并记录日志文件
 *
 * @author galudisu
 */
public class LogUtils {
  public static String getBlock(Object msg) {
    if (msg == null) {
      msg = "";
    }
    return "[" + msg.toString() + "]";
  }
}
