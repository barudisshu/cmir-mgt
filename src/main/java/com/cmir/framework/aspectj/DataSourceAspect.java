/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.aspectj;

import com.cmir.common.annotation.DataSource;
import com.cmir.common.utils.StringUtils;
import com.cmir.framework.datasource.DynamicDataSourceContextHolder;
import java.util.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 多数据源处理
 *
 * @author galudisu
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Pointcut(
      "@annotation(com.cmir.common.annotation.DataSource)"
          + "|| @within(com.cmir.common.annotation.DataSource)")
  public void dsPointCut() {}

  @Around("dsPointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    DataSource dataSource = getDataSource(point);

    if (StringUtils.isNotNull(dataSource)) {
      DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
    }

    try {
      return point.proceed();
    } finally {
      // 销毁数据源 在执行方法之后
      DynamicDataSourceContextHolder.clearDataSourceType();
    }
  }

  /** 获取需要切换的数据源 */
  public DataSource getDataSource(ProceedingJoinPoint point) {
    MethodSignature signature = (MethodSignature) point.getSignature();
    DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
    if (Objects.nonNull(dataSource)) {
      return dataSource;
    }

    return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
  }
}
