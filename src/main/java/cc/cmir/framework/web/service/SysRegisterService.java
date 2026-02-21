package cc.cmir.framework.web.service;

import cc.cmir.common.constant.CacheConstants;
import cc.cmir.common.constant.Constants;
import cc.cmir.common.constant.UserConstants;
import cc.cmir.common.core.domain.entity.SysUser;
import cc.cmir.common.core.domain.model.RegisterBody;
import cc.cmir.common.core.redis.RedisCache;
import cc.cmir.common.exception.user.CaptchaException;
import cc.cmir.common.exception.user.CaptchaExpireException;
import cc.cmir.common.utils.DateUtils;
import cc.cmir.common.utils.MessageUtils;
import cc.cmir.common.utils.SecurityUtils;
import cc.cmir.common.utils.StringUtils;
import cc.cmir.framework.manager.factory.AsyncFactory;
import cc.cmir.system.service.ISysConfigService;
import cc.cmir.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 注册校验方法
 *
 * @author Galudisu
 */
@Component
public class SysRegisterService {
  private final ISysUserService userService;

  private final ISysConfigService configService;

  private final RedisCache redisCache;

  private final SimpleAsyncTaskScheduler simpleAsyncTaskScheduler;

  public SysRegisterService(
      ISysUserService userService,
      ISysConfigService configService,
      RedisCache redisCache,
      @Qualifier("scheduledExecutorService") SimpleAsyncTaskScheduler simpleAsyncTaskScheduler) {
    this.userService = userService;
    this.configService = configService;
    this.redisCache = redisCache;
    this.simpleAsyncTaskScheduler = simpleAsyncTaskScheduler;
  }

  /** 注册 */
  public String register(RegisterBody registerBody) {
    String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
    SysUser sysUser = new SysUser();
    sysUser.setUserName(username);

    // 验证码开关
    boolean captchaEnabled = configService.selectCaptchaEnabled();
    if (captchaEnabled) {
      validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
    }

    if (StringUtils.isEmpty(username)) {
      msg = "用户名不能为空";
    } else if (StringUtils.isEmpty(password)) {
      msg = "用户密码不能为空";
    } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
        || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
      msg = "账户长度必须在2到20个字符之间";
    } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
        || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
      msg = "密码长度必须在5到20个字符之间";
    } else if (!userService.checkUserNameUnique(sysUser)) {
      msg = "保存用户'" + username + "'失败，注册账号已存在";
    } else {
      sysUser.setNickName(username);
      sysUser.setPwdUpdateDate(DateUtils.getNowDate());
      sysUser.setPassword(SecurityUtils.encryptPassword(password));
      boolean regFlag = userService.registerUser(sysUser);
      if (!regFlag) {
        msg = "注册失败,请联系系统管理人员";
      } else {
        simpleAsyncTaskScheduler.execute(
            AsyncFactory.recordLogininfor(
                username, Constants.REGISTER, MessageUtils.message("user.register.success")));
      }
    }
    return msg;
  }

  /**
   * 校验验证码
   *
   * @param username 用户名
   * @param code 验证码
   * @param uuid 唯一标识
   * @return 结果
   */
  public void validateCaptcha(String username, String code, String uuid) {
    String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
    String captcha = redisCache.getCacheObject(verifyKey);
    redisCache.deleteObject(verifyKey);
    if (captcha == null) {
      throw new CaptchaExpireException();
    }
    if (!code.equalsIgnoreCase(captcha)) {
      throw new CaptchaException();
    }
  }
}
