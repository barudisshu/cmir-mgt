/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.domain;

import com.cmir.common.utils.StringUtils;

/**
 * 缓存信息
 *
 * @author galudisu
 */
public class SysCache {
  /** 缓存名称 */
  private String cacheName = "";

  /** 缓存键名 */
  private String cacheKey = "";

  /** 缓存内容 */
  private String cacheValue = "";

  /** 备注 */
  private String remark = "";

  public SysCache() {}

  public SysCache(String cacheName, String remark) {
    this.cacheName = cacheName;
    this.remark = remark;
  }

  public SysCache(String cacheName, String cacheKey, String cacheValue) {
    this.cacheName = StringUtils.replace(cacheName, ":", "");
    this.cacheKey = StringUtils.replace(cacheKey, cacheName, "");
    this.cacheValue = cacheValue;
  }

  public String getCacheName() {
    return cacheName;
  }

  public void setCacheName(String cacheName) {
    this.cacheName = cacheName;
  }

  public String getCacheKey() {
    return cacheKey;
  }

  public void setCacheKey(String cacheKey) {
    this.cacheKey = cacheKey;
  }

  public String getCacheValue() {
    return cacheValue;
  }

  public void setCacheValue(String cacheValue) {
    this.cacheValue = cacheValue;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
