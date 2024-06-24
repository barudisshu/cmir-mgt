/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.security.handle;

import com.alibaba.fastjson2.JSON;
import com.cmir.common.constant.HttpStatus;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.utils.ServletUtils;
import com.cmir.common.utils.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理类 返回未授权
 *
 * @author galudisu
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

  @Override
  public void commence(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
      throws IOException {
    int code = HttpStatus.UNAUTHORIZED;
    String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
    ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
  }
}
