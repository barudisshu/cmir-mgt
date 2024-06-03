/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.file;

/**
 * 文件名大小限制异常类
 *
 * @author galudisu
 */
public class FileSizeLimitExceededException extends FileException {

  public FileSizeLimitExceededException(long defaultMaxSize) {
    super("upload.exceed.maxSize", new Object[] {defaultMaxSize});
  }
}
