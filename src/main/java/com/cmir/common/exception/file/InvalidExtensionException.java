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

import java.util.Arrays;

/**
 * 文件上传 误异常类
 *
 * @author galudisu
 */
public class InvalidExtensionException extends FileUploadException {

  private String[] allowedExtension;
  private String extension;
  private String filename;

  public InvalidExtensionException(String[] allowedExtension, String extension, String filename) {
    super(
        "文件["
            + filename
            + "]后缀["
            + extension
            + "]不正确，请上传"
            + Arrays.toString(allowedExtension)
            + "格式");
    this.allowedExtension = allowedExtension;
    this.extension = extension;
    this.filename = filename;
  }

  public String[] getAllowedExtension() {
    return allowedExtension;
  }

  public String getExtension() {
    return extension;
  }

  public String getFilename() {
    return filename;
  }

  public static class InvalidImageExtensionException extends InvalidExtensionException {

    public InvalidImageExtensionException(
        String[] allowedExtension, String extension, String filename) {
      super(allowedExtension, extension, filename);
    }
  }

  public static class InvalidFlashExtensionException extends InvalidExtensionException {

    public InvalidFlashExtensionException(
        String[] allowedExtension, String extension, String filename) {
      super(allowedExtension, extension, filename);
    }
  }

  public static class InvalidMediaExtensionException extends InvalidExtensionException {

    public InvalidMediaExtensionException(
        String[] allowedExtension, String extension, String filename) {
      super(allowedExtension, extension, filename);
    }
  }

  public static class InvalidVideoExtensionException extends InvalidExtensionException {

    public InvalidVideoExtensionException(
        String[] allowedExtension, String extension, String filename) {
      super(allowedExtension, extension, filename);
    }
  }
}
