/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
