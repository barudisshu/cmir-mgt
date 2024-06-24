/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.interceptor;

import com.alibaba.fastjson2.JSON;
import com.cmir.common.annotation.RepeatSubmit;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.utils.ServletUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 防止重复提交拦截器
 *
 * @author galudisu
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
      if (annotation != null) {
        if (this.isRepeatSubmit(request, annotation)) {
          AjaxResult ajaxResult = AjaxResult.error(annotation.message());
          ServletUtils.renderString(response, JSON.toJSONString(ajaxResult));
          return false;
        }
      }
      return true;
    } else {
      return true;
    }
  }

  /**
   * 验证是否重复提交由子类实现具体的防重复提交的规则
   *
   * @param request 请求信息
   * @param annotation 防重复注解参数
   * @return 结果
   * @throws Exception
   */
  public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
