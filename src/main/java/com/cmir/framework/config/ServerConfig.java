/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir.framework.config;

import com.cmir.common.utils.ServletUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 服务相关配置
 *
 * @author galudisu
 */
@Component
public class ServerConfig {
  /**
   * 获取完整的请求路径，包括：域名，端口，上下文访问路径
   *
   * @return 服务地址
   */
  public String getUrl() {
    HttpServletRequest request = ServletUtils.getRequest();
    return getDomain(request);
  }

  public static String getDomain(HttpServletRequest request) {
    StringBuffer url = request.getRequestURL();
    String contextPath = request.getServletContext().getContextPath();
    return url.delete(url.length() - request.getRequestURI().length(), url.length())
        .append(contextPath)
        .toString();
  }
}
