/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.web.domain.server;

/**
 * 系统相关信息
 *
 * @author galudisu
 */
public class Sys {
  /** 服务器名称 */
  private String computerName;

  /** 服务器Ip */
  private String computerIp;

  /** 项目路径 */
  private String userDir;

  /** 操作系统 */
  private String osName;

  /** 系统架构 */
  private String osArch;

  public String getComputerName() {
    return computerName;
  }

  public void setComputerName(String computerName) {
    this.computerName = computerName;
  }

  public String getComputerIp() {
    return computerIp;
  }

  public void setComputerIp(String computerIp) {
    this.computerIp = computerIp;
  }

  public String getUserDir() {
    return userDir;
  }

  public void setUserDir(String userDir) {
    this.userDir = userDir;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public String getOsArch() {
    return osArch;
  }

  public void setOsArch(String osArch) {
    this.osArch = osArch;
  }
}
