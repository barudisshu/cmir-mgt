/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.quartz.task;

import com.cmir.common.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author galudisu
 */
@Component("ryTask")
public class RyTask {
  public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
    System.out.println(
        StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
  }

  public void ryParams(String params) {
    System.out.println("执行有参方法：" + params);
  }

  public void ryNoParams() {
    System.out.println("执行无参方法");
  }
}
