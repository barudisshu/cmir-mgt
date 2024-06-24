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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 文件上传异常类
 *
 * @author galudisu
 */
public class FileUploadException extends Exception {

  private final Throwable cause;

  public FileUploadException() {
    this(null, null);
  }

  public FileUploadException(final String msg) {
    this(msg, null);
  }

  public FileUploadException(String msg, Throwable cause) {
    super(msg);
    this.cause = cause;
  }

  @Override
  public void printStackTrace(PrintStream stream) {
    super.printStackTrace(stream);
    if (cause != null) {
      stream.println("Caused by:");
      cause.printStackTrace(stream);
    }
  }

  @Override
  public void printStackTrace(PrintWriter writer) {
    super.printStackTrace(writer);
    if (cause != null) {
      writer.println("Caused by:");
      cause.printStackTrace(writer);
    }
  }

  @Override
  public Throwable getCause() {
    return cause;
  }
}
