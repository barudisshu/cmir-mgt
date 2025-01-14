/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.quartz.util;

import java.text.ParseException;
import java.util.Date;
import org.quartz.CronExpression;

/**
 * cron表达式工具类
 *
 * @author galudisu
 */
public class CronUtils {
  /**
   * 返回一个布尔值代表一个给定的Cron表达式的有效性
   *
   * @param cronExpression Cron表达式
   * @return boolean 表达式是否有效
   */
  public static boolean isValid(String cronExpression) {
    return CronExpression.isValidExpression(cronExpression);
  }

  /**
   * 返回一个字符串值,表示该消息无效Cron表达式给出有效性
   *
   * @param cronExpression Cron表达式
   * @return String 无效时返回表达式错误描述,如果有效返回null
   */
  public static String getInvalidMessage(String cronExpression) {
    try {
      new CronExpression(cronExpression);
      return null;
    } catch (ParseException pe) {
      return pe.getMessage();
    }
  }

  /**
   * 返回下一个执行时间根据给定的Cron表达式
   *
   * @param cronExpression Cron表达式
   * @return Date 下次Cron表达式执行时间
   */
  public static Date getNextExecution(String cronExpression) {
    try {
      CronExpression cron = new CronExpression(cronExpression);
      return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
    } catch (ParseException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
