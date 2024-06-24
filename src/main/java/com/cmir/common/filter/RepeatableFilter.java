/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.filter;

import com.cmir.common.utils.StringUtils;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.http.MediaType;

/**
 * Repeatable 过滤器
 *
 * @author galudisu
 */
public class RepeatableFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletRequest requestWrapper = null;
    if (request instanceof HttpServletRequest req
        && StringUtils.startsWithIgnoreCase(
            request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
      requestWrapper = new RepeatedlyRequestWrapper(req, response);
    }
    if (null == requestWrapper) {
      chain.doFilter(request, response);
    } else {
      chain.doFilter(requestWrapper, response);
    }
  }

  @Override
  public void destroy() {}
}
