package cc.cmir.web.controller.system;

import cc.cmir.common.annotation.Log;
import cc.cmir.common.config.CmirConfig;
import cc.cmir.common.core.controller.BaseController;
import cc.cmir.common.core.domain.AjaxResult;
import cc.cmir.common.core.domain.entity.SysUser;
import cc.cmir.common.core.domain.model.LoginUser;
import cc.cmir.common.enums.BusinessType;
import cc.cmir.common.utils.DateUtils;
import cc.cmir.common.utils.SecurityUtils;
import cc.cmir.common.utils.StringUtils;
import cc.cmir.common.utils.file.FileUploadUtils;
import cc.cmir.common.utils.file.FileUtils;
import cc.cmir.common.utils.file.MimeTypeUtils;
import cc.cmir.framework.web.service.TokenService;
import cc.cmir.system.service.ISysUserService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 *
 * @author Galudisu
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
  private final ISysUserService userService;

  private final TokenService tokenService;

  public SysProfileController(ISysUserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  /** 个人信息 */
  @GetMapping
  public AjaxResult profile() {
    LoginUser loginUser = getLoginUser();
    SysUser user = loginUser.getUser();
    AjaxResult ajax = AjaxResult.success(user);
    ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
    ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
    return ajax;
  }

  /** 修改用户 */
  @Log(title = "个人信息", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult updateProfile(@RequestBody SysUser user) {
    LoginUser loginUser = getLoginUser();
    SysUser currentUser = loginUser.getUser();
    currentUser.setNickName(user.getNickName());
    currentUser.setEmail(user.getEmail());
    currentUser.setPhonenumber(user.getPhonenumber());
    currentUser.setSex(user.getSex());
    if (StringUtils.isNotEmpty(user.getPhonenumber())
        && !userService.checkPhoneUnique(currentUser)) {
      return error("修改用户'" + loginUser.getUsername() + "'失败，手机号码已存在");
    }
    if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(currentUser)) {
      return error("修改用户'" + loginUser.getUsername() + "'失败，邮箱账号已存在");
    }
    if (userService.updateUserProfile(currentUser) > 0) {
      // 更新缓存用户信息
      tokenService.setLoginUser(loginUser);
      return success();
    }
    return error("修改个人信息异常，请联系管理员");
  }

  /** 重置密码 */
  @Log(title = "个人信息", businessType = BusinessType.UPDATE)
  @PutMapping("/updatePwd")
  public AjaxResult updatePwd(@RequestBody Map<String, String> params) {
    String oldPassword = params.get("oldPassword");
    String newPassword = params.get("newPassword");
    LoginUser loginUser = getLoginUser();
    Long userId = loginUser.getUserId();
    SysUser user = userService.selectUserById(userId);
    String password = user.getPassword();
    if (!SecurityUtils.matchesPassword(oldPassword, password)) {
      return error("修改密码失败，旧密码错误");
    }
    if (SecurityUtils.matchesPassword(newPassword, password)) {
      return error("新密码不能与旧密码相同");
    }
    newPassword = SecurityUtils.encryptPassword(newPassword);
    if (userService.resetUserPwd(userId, newPassword) > 0) {
      // 更新缓存用户密码&密码最后更新时间
      loginUser.getUser().setPwdUpdateDate(DateUtils.getNowDate());
      loginUser.getUser().setPassword(newPassword);
      tokenService.setLoginUser(loginUser);
      return success();
    }
    return error("修改密码异常，请联系管理员");
  }

  /** 头像上传 */
  @Log(title = "用户头像", businessType = BusinessType.UPDATE)
  @PostMapping("/avatar")
  public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
    if (!file.isEmpty()) {
      LoginUser loginUser = getLoginUser();
      String avatar =
          FileUploadUtils.upload(
              CmirConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION, true);
      if (userService.updateUserAvatar(loginUser.getUserId(), avatar)) {
        String oldAvatar = loginUser.getUser().getAvatar();
        if (StringUtils.isNotEmpty(oldAvatar)) {
          FileUtils.deleteFile(CmirConfig.getProfile() + FileUtils.stripPrefix(oldAvatar));
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("imgUrl", avatar);
        // 更新缓存用户头像
        loginUser.getUser().setAvatar(avatar);
        tokenService.setLoginUser(loginUser);
        return ajax;
      }
    }
    return error("上传图片异常，请联系管理员");
  }
}
