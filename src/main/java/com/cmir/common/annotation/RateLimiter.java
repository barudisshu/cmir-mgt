/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.annotation;

import com.cmir.common.constant.CacheConstants;
import com.cmir.common.enums.LimitType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 *
 * @author galudisu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
  /** 限流key */
  public String key() default CacheConstants.RATE_LIMIT_KEY;

  /** 限流时间,单位秒 */
  public int time() default 60;

  /** 限流次数 */
  public int count() default 100;

  /** 限流类型 */
  public LimitType limitType() default LimitType.DEFAULT;
}
