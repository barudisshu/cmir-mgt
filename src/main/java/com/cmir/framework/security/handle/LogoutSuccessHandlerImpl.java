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
import com.cmir.common.constant.Constants;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.utils.MessageUtils;
import com.cmir.common.utils.ServletUtils;
import com.cmir.common.utils.StringUtils;
import com.cmir.framework.manager.AsyncManager;
import com.cmir.framework.manager.factory.AsyncFactory;
import com.cmir.framework.web.service.TokenService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 *
 * @author galudisu
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
  @Autowired private TokenService tokenService;

  /**
   * 退出处理
   *
   * @return
   */
  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    LoginUser loginUser = tokenService.getLoginUser(request);
    if (StringUtils.isNotNull(loginUser)) {
      String userName = loginUser.getUsername();
      // 删除用户缓存记录
      tokenService.delLoginUser(loginUser.getToken());
      // 记录用户退出日志
      AsyncManager.me()
          .execute(
              AsyncFactory.recordLogininfor(
                  userName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
    }
    ServletUtils.renderString(
        response,
        JSON.toJSONString(AjaxResult.success(MessageUtils.message("user.logout.success"))));
  }
}
