/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.generator.util;

import com.cmir.common.constant.Constants;
import java.util.Properties;
import org.apache.velocity.app.Velocity;

/**
 * VelocityEngine工厂
 *
 * @author galudisu
 */
public class VelocityInitializer {
  /** 初始化vm方法 */
  public static void initVelocity() {
    Properties p = new Properties();
    try {
      // 加载classpath目录下的vm文件
      p.setProperty(
          "resource.loader.file.class",
          "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      // 定义字符集
      p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
      // 初始化Velocity引擎，指定配置Properties
      Velocity.init(p);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
