package cc.cmir.common.exception.user;

/**
 * 黑名单IP异常类
 *
 * @author Galudisu
 */
public class BlackListException extends UserException {

  public BlackListException() {
    super("login.blocked", null);
  }
}
