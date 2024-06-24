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

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.cmir.common.enums.DataSourceType;
import com.cmir.common.utils.spring.SpringUtils;
import com.cmir.framework.config.properties.DruidProperties;
import com.cmir.framework.datasource.DynamicDataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * druid 配置多数据源
 *
 * @author galudisu
 */
@Configuration
public class DruidConfig {
  @Bean
  @ConfigurationProperties("spring.datasource.druid.master")
  public DataSource masterDataSource(DruidProperties druidProperties) {
    DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
    return druidProperties.dataSource(dataSource);
  }

  @Bean
  @ConfigurationProperties("spring.datasource.druid.slave")
  @ConditionalOnProperty(
      prefix = "spring.datasource.druid.slave",
      name = "enabled",
      havingValue = "true")
  public DataSource slaveDataSource(DruidProperties druidProperties) {
    DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
    return druidProperties.dataSource(dataSource);
  }

  @Bean(name = "dynamicDataSource")
  @Primary
  public DynamicDataSource dataSource(DataSource masterDataSource) {
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
    setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
    return new DynamicDataSource(masterDataSource, targetDataSources);
  }

  /**
   * 设置数据源
   *
   * @param targetDataSources 备选数据源集合
   * @param sourceName 数据源名称
   * @param beanName bean名称
   */
  public void setDataSource(
      Map<Object, Object> targetDataSources, String sourceName, String beanName) {
    try {
      DataSource dataSource = SpringUtils.getBean(beanName);
      targetDataSources.put(sourceName, dataSource);
    } catch (Exception e) {
      // NOSONAR
    }
  }

  /** 去除监控页面底部的广告 */
  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  @ConditionalOnProperty(
      name = "spring.datasource.druid.statViewServlet.enabled",
      havingValue = "true")
  public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
    // 获取web监控页面的参数
    DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
    // 提取common.js的配置路径
    String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
    String commonJsPattern = pattern.replaceAll("\\w*", "js/common.js");
    final String filePath = "support/http/resources/js/common.js";
    // 创建filter进行过滤
    Filter filter =
        new Filter() {
          @Override
          public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
            // NOSONAR
          }

          @Override
          public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
              throws IOException, ServletException {
            chain.doFilter(request, response);
            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();
            // 获取common.js
            String text = Utils.readFromResource(filePath);
            // 正则替换banner, 除去底部的广告信息
            text = text.replaceAll("<a.*?banner\"></a><br/>", "");
            text = text.replaceAll("powered.*?shrek.wang</a>", "");
            response.getWriter().write(text);
          }

          @Override
          public void destroy() {
            // NOSONAR
          }
        };
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(filter);
    registrationBean.addUrlPatterns(commonJsPattern);
    return registrationBean;
  }
}
