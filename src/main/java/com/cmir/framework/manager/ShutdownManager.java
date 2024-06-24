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

import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 确保应用退出时能关闭后台线程
 *
 * @author galudisu
 */
@Component
public class ShutdownManager {
  private static final Logger logger = LoggerFactory.getLogger("sys-user");

  @PreDestroy
  public void destroy() {
    shutdownAsyncManager();
  }

  /** 停止异步执行任务 */
  private void shutdownAsyncManager() {
    try {
      logger.info("====关闭后台任务任务线程池====");
      AsyncManager.me().shutdown();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
