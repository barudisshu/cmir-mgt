/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.exception.file;

import com.cmir.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author galudisu
 */
public class FileException extends BaseException {

  public FileException(String code, Object[] args) {
    super("file", code, args, null);
  }
}
