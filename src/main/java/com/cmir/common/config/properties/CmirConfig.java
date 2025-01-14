/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author galudisu
 */
@Component
@ConfigurationProperties(prefix = "cmir")
public class CmirConfig {
  /** 项目名称 */
  private String name;

  /** 版本 */
  private String version;

  /** 版权年份 */
  private String copyrightYear;

  /** 上传路径 */
  private static String profile;

  /** 获取地址开关 */
  private static boolean addressEnabled;

  /** 验证码类型 */
  private static String captchaType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCopyrightYear() {
    return copyrightYear;
  }

  public void setCopyrightYear(String copyrightYear) {
    this.copyrightYear = copyrightYear;
  }

  public static String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    CmirConfig.profile = profile;
  }

  public static boolean isAddressEnabled() {
    return addressEnabled;
  }

  public void setAddressEnabled(boolean addressEnabled) {
    CmirConfig.addressEnabled = addressEnabled;
  }

  public static String getCaptchaType() {
    return captchaType;
  }

  public void setCaptchaType(String captchaType) {
    CmirConfig.captchaType = captchaType;
  }

  /** 获取导入上传路径 */
  public static String getImportPath() {
    return getProfile() + "/import";
  }

  /** 获取头像上传路径 */
  public static String getAvatarPath() {
    return getProfile() + "/avatar";
  }

  /** 获取下载路径 */
  public static String getDownloadPath() {
    return getProfile() + "/download/";
  }

  /** 获取上传路径 */
  public static String getUploadPath() {
    return getProfile() + "/upload";
  }
}
