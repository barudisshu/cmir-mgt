/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.file;

/**
 * 文件名称超长限制异常类
 *
 * @author galudisu
 */
public class FileNameLengthLimitExceededException extends FileException {

  public FileNameLengthLimitExceededException(int defaultFileNameLength) {
    super("upload.filename.exceed.length", new Object[] {defaultFileNameLength});
  }
}
