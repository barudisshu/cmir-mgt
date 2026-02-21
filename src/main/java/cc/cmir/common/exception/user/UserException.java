package cc.cmir.common.exception.user;

import cc.cmir.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author Galudisu
 */
public class UserException extends BaseException {

  public UserException(String code, Object[] args) {
    super("user", code, args, null);
  }
}
