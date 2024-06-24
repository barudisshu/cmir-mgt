/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils;

/**
 * 脱敏工具类
 *
 * @author galudisu
 */
public class DesensitizedUtil {
  /**
   * 密码的全部字符都用*代替，比如：******
   *
   * @param password 密码
   * @return 脱敏后的密码
   */
  public static String password(String password) {
    if (StringUtils.isBlank(password)) {
      return StringUtils.EMPTY;
    }
    return StringUtils.repeat('*', password.length());
  }

  /**
   * 车牌中间用*代替，如果是错误的车牌，不处理
   *
   * @param carLicense 完整的车牌号
   * @return 脱敏后的车牌
   */
  public static String carLicense(String carLicense) {
    if (StringUtils.isBlank(carLicense)) {
      return StringUtils.EMPTY;
    }
    // 普通车牌
    if (carLicense.length() == 7) {
      carLicense = StringUtils.hide(carLicense, 3, 6);
    } else if (carLicense.length() == 8) {
      // 新能源车牌
      carLicense = StringUtils.hide(carLicense, 3, 7);
    }
    return carLicense;
  }
}
