/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.common.enums;

/**
 * 限流类型
 *
 * @author galudisu
 */
public enum LimitType {
  /** 默认策略全局限流 */
  DEFAULT,

  /** 根据请求者IP进行限流 */
  IP
}
