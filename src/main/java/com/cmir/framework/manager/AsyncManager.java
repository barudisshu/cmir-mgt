/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.manager;

import com.cmir.common.utils.Threads;
import com.cmir.common.utils.spring.SpringUtils;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author galudisu
 */
public class AsyncManager {
  /** 操作延迟10毫秒 */
  private final int OPERATE_DELAY_TIME = 10;

  /** 异步操作任务调度线程池 */
  private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

  /** 单例模式 */
  private AsyncManager() {}

  private static AsyncManager me = new AsyncManager();

  public static AsyncManager me() {
    return me;
  }

  /**
   * 执行任务
   *
   * @param task 任务
   */
  public void execute(TimerTask task) {
    executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
  }

  /** 停止任务线程池 */
  public void shutdown() {
    Threads.shutdownAndAwaitTermination(executor);
  }
}
