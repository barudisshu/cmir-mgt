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

import com.cmir.common.enums.BusinessType;
import com.cmir.common.enums.OperatorType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志记录注解
 *
 * @author galudisu
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
  /** 模块 */
  public String title() default "";

  /** 功能 */
  public BusinessType businessType() default BusinessType.OTHER;

  /** 操作人类别 */
  public OperatorType operatorType() default OperatorType.MANAGE;

  /** 是否保存请求的参数 */
  public boolean isSaveRequestData() default true;

  /** 是否保存响应的参数 */
  public boolean isSaveResponseData() default true;

  /** 排除指定的请求参数 */
  public String[] excludeParamNames() default {};
}
