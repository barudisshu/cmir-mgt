/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
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
