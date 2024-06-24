/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.config;

import com.cmir.common.filter.RepeatableFilter;
import com.cmir.common.filter.XssFilter;
import com.cmir.common.utils.StringUtils;
import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter配置
 *
 * @author galudisu
 */
@Configuration
public class FilterConfig {
  @Value("${xss.excludes}")
  private String excludes;

  @Value("${xss.urlPatterns}")
  private String urlPatterns;

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  @ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
  public FilterRegistrationBean xssFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new XssFilter());
    registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
    registration.setName("xssFilter");
    registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
    Map<String, String> initParameters = new HashMap<String, String>();
    initParameters.put("excludes", excludes);
    registration.setInitParameters(initParameters);
    return registration;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public FilterRegistrationBean someFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new RepeatableFilter());
    registration.addUrlPatterns("/*");
    registration.setName("repeatableFilter");
    registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
    return registration;
  }
}
