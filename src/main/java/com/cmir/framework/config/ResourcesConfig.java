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

import com.cmir.common.config.properties.CmirConfig;
import com.cmir.common.constant.Constants;
import com.cmir.framework.interceptor.RepeatSubmitInterceptor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 *
 * @author galudisu
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
  @Autowired private RepeatSubmitInterceptor repeatSubmitInterceptor;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    /** 本地文件上传路径 */
    registry
        .addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
        .addResourceLocations("file:" + CmirConfig.getProfile() + "/");

    /** swagger配置 */
    registry
        .addResourceHandler("/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .setCacheControl(CacheControl.maxAge(5, TimeUnit.HOURS).cachePublic());
    ;
  }

  /** 自定义拦截规则 */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
  }

  /** 跨域配置 */
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // 设置访问源地址
    config.addAllowedOriginPattern("*");
    // 设置访问源请求头
    config.addAllowedHeader("*");
    // 设置访问源请求方法
    config.addAllowedMethod("*");
    // 有效期 1800秒
    config.setMaxAge(1800L);
    // 添加映射路径，拦截一切请求
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    // 返回新的CorsFilter
    return new CorsFilter(source);
  }
}
