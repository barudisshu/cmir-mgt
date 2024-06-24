/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.uuid;

/**
 * ID生成器工具类
 *
 * @author galudisu
 */
public class IdUtils {
  /**
   * 获取随机UUID
   *
   * @return 随机UUID
   */
  public static String randomUUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * 简化的UUID，去掉了横线
   *
   * @return 简化的UUID，去掉了横线
   */
  public static String simpleUUID() {
    return UUID.randomUUID().toString(true);
  }

  /**
   * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
   *
   * @return 随机UUID
   */
  public static String fastUUID() {
    return UUID.fastUUID().toString();
  }

  /**
   * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
   *
   * @return 简化的UUID，去掉了横线
   */
  public static String fastSimpleUUID() {
    return UUID.fastUUID().toString(true);
  }
}
