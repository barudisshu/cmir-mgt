/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.constant;

/**
 * 缓存的key 常量
 *
 * @author galudisu
 */
public class CacheConstants {
  /** 登录用户 redis key */
  public static final String LOGIN_TOKEN_KEY = "login_tokens:";

  /** 验证码 redis key */
  public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

  /** 参数管理 cache key */
  public static final String SYS_CONFIG_KEY = "sys_config:";

  /** 字典管理 cache key */
  public static final String SYS_DICT_KEY = "sys_dict:";

  /** 防重提交 redis key */
  public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

  /** 限流 redis key */
  public static final String RATE_LIMIT_KEY = "rate_limit:";

  /** 登录账户密码错误次数 redis key */
  public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
