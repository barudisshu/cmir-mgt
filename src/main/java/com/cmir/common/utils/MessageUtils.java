/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils;

import com.cmir.common.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 *
 * @author galudisu
 */
public class MessageUtils {
  /**
   * 根据消息键和参数 获取消息 委托给spring messageSource
   *
   * @param code 消息键
   * @param args 参数
   * @return 获取国际化翻译值
   */
  public static String message(String code, Object... args) {
    MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
    return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
  }
}
