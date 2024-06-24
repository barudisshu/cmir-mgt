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

import com.cmir.quartz.domain.SysJob;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author galudisu
 */
public class QuartzJobExecution extends AbstractQuartzJob {
  @Override
  protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
    JobInvokeUtil.invokeMethod(sysJob);
  }
}
