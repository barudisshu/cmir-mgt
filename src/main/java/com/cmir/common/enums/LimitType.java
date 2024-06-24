/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
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
