package cc.cmir.framework.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * @author Galudisu
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
  // 核心线程池大小
  private static final int CORE_POOL_SIZE = 50;

  // 最大可创建的线程数
  private static final int MAX_POOL_SIZE = 200;

  // 队列最大长度
  private static final int QUEUE_CAPACITY = 1000;

  // 线程池维护线程所允许的空闲时间
  private static final int KEEP_ALIVE_SECONDS = 300;

  @Bean(name = "threadPoolTaskExecutor")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setMaxPoolSize(MAX_POOL_SIZE);
    executor.setCorePoolSize(CORE_POOL_SIZE);
    executor.setQueueCapacity(QUEUE_CAPACITY);
    executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
    executor.setVirtualThreads(true);
    // 线程池对拒绝任务(无线程可用)的处理策略
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return executor;
  }

  /** 执行周期性或定时任务 */
  @Bean(name = "scheduledExecutorService")
  protected SimpleAsyncTaskScheduler scheduledExecutorService() {
    SimpleAsyncTaskScheduler scheduler = new SimpleAsyncTaskScheduler();
    scheduler.setVirtualThreads(true);
    scheduler.setConcurrencyLimit(4);
    scheduler.setThreadNamePrefix("schedule-pool-%d");
    return scheduler;
  }
}
