package cc.cmir.common.exception.file;

import cc.cmir.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author Galudisu
 */
public class FileException extends BaseException {

  public FileException(String code, Object[] args) {
    super("file", code, args, null);
  }
}
