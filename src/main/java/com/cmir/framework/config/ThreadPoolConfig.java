/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.config;

import com.cmir.common.utils.Threads;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * @author galudisu
 */
@Configuration
public class ThreadPoolConfig {
  // 核心线程池大小
  private static final int corePoolSize = 50;

  // 最大可创建的线程数
  private static final int maxPoolSize = 200;

  // 队列最大长度
  private static final int queueCapacity = 1000;

  // 线程池维护线程所允许的空闲时间
  private static final int keepAliveSeconds = 300;

  @Bean(name = "threadPoolTaskExecutor")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setMaxPoolSize(maxPoolSize);
    executor.setCorePoolSize(corePoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    // 线程池对拒绝任务(无线程可用)的处理策略
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return executor;
  }

  /** 执行周期性或定时任务 */
  @Bean(name = "scheduledExecutorService")
  protected ScheduledExecutorService scheduledExecutorService() {
    return new ScheduledThreadPoolExecutor(
        corePoolSize,
        new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
        new ThreadPoolExecutor.CallerRunsPolicy()) {
      @Override
      protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Threads.printException(r, t);
      }
    };
  }
}
