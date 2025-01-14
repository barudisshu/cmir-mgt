/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.web.controller.common;

import com.cmir.common.config.properties.CmirConfig;
import com.cmir.common.constant.CacheConstants;
import com.cmir.common.constant.Constants;
import com.cmir.common.core.domain.AjaxResult;
import com.cmir.common.core.redis.RedisCache;
import com.cmir.common.utils.sign.Base64;
import com.cmir.common.utils.uuid.IdUtils;
import com.cmir.system.service.ISysConfigService;
import com.google.code.kaptcha.Producer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码操作处理
 *
 * @author galudisu
 */
@RestController
public class CaptchaController {
  @Resource(name = "captchaProducer")
  private Producer captchaProducer;

  @Resource(name = "captchaProducerMath")
  private Producer captchaProducerMath;

  @Autowired private RedisCache redisCache;

  @Autowired private ISysConfigService configService;

  /** 生成验证码 */
  @GetMapping("/captchaImage")
  public AjaxResult getCode(HttpServletResponse response) throws IOException {
    AjaxResult ajax = AjaxResult.success();
    boolean captchaEnabled = configService.selectCaptchaEnabled();
    ajax.put("captchaEnabled", captchaEnabled);
    if (!captchaEnabled) {
      return ajax;
    }

    // 保存验证码信息
    String uuid = IdUtils.simpleUUID();
    String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

    String capStr = null, code = null;
    BufferedImage image = null;

    // 生成验证码
    String captchaType = CmirConfig.getCaptchaType();
    if ("math".equals(captchaType)) {
      String capText = captchaProducerMath.createText();
      capStr = capText.substring(0, capText.lastIndexOf("@"));
      code = capText.substring(capText.lastIndexOf("@") + 1);
      image = captchaProducerMath.createImage(capStr);
    } else if ("char".equals(captchaType)) {
      capStr = code = captchaProducer.createText();
      image = captchaProducer.createImage(capStr);
    }

    redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
    // 转换流信息写出
    FastByteArrayOutputStream os = new FastByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpg", os);
    } catch (IOException e) {
      return AjaxResult.error(e.getMessage());
    }

    ajax.put("uuid", uuid);
    ajax.put("img", Base64.encode(os.toByteArray()));
    return ajax;
  }
}
