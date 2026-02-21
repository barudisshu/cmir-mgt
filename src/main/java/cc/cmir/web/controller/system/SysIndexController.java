package cc.cmir.web.controller.system;

import cc.cmir.common.config.CmirConfig;
import cc.cmir.common.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author Galudisu
 */
@RestController
public class SysIndexController {
  /** 系统基础配置 */
  private final CmirConfig cmirConfig;

  public SysIndexController(CmirConfig cmirConfig) {
    this.cmirConfig = cmirConfig;
  }

  /** 访问首页，提示语 */
  @RequestMapping("/")
  public String index() {
    return StringUtils.format(
        "欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cmirConfig.getName(), cmirConfig.getVersion());
  }
}
