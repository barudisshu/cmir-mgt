package cc.cmir.framework.security.handle;

import cc.cmir.common.constant.Constants;
import cc.cmir.common.core.domain.AjaxResult;
import cc.cmir.common.core.domain.model.LoginUser;
import cc.cmir.common.utils.MessageUtils;
import cc.cmir.common.utils.ServletUtils;
import cc.cmir.common.utils.StringUtils;
import cc.cmir.framework.manager.AsyncManager;
import cc.cmir.framework.manager.factory.AsyncFactory;
import cc.cmir.framework.web.service.TokenService;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 *
 * @author Galudisu
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
