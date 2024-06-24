/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.constant;

/**
 * 任务调度通用常量
 *
 * @author galudisu
 */
public class ScheduleConstants {
  public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

  /** 执行目标key */
  public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

  /** 默认 */
  public static final String MISFIRE_DEFAULT = "0";

  /** 立即触发执行 */
  public static final String MISFIRE_IGNORE_MISFIRES = "1";

  /** 触发一次执行 */
  public static final String MISFIRE_FIRE_AND_PROCEED = "2";

  /** 不触发立即执行 */
  public static final String MISFIRE_DO_NOTHING = "3";

  public enum Status {
    /** 正常 */
    NORMAL("0"),
    /** 暂停 */
    PAUSE("1");

    private String value;

    private Status(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
