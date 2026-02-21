package cc.cmir.framework.security.handle;

import cc.cmir.common.constant.HttpStatus;
import cc.cmir.common.core.domain.AjaxResult;
import cc.cmir.common.utils.ServletUtils;
import cc.cmir.common.utils.StringUtils;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理类 返回未授权
 *
 * @author Galudisu
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
