/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.web.domain.server;

import com.cmir.common.utils.Arith;

/**
 * 內存相关信息
 *
 * @author galudisu
 */
public class Mem {
  /** 内存总量 */
  private double total;

  /** 已用内存 */
  private double used;

  /** 剩余内存 */
  private double free;

  public double getTotal() {
    return Arith.div(total, (1024 * 1024 * 1024), 2);
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public double getUsed() {
    return Arith.div(used, (1024 * 1024 * 1024), 2);
  }

  public void setUsed(long used) {
    this.used = used;
  }

  public double getFree() {
    return Arith.div(free, (1024 * 1024 * 1024), 2);
  }

  public void setFree(long free) {
    this.free = free;
  }

  public double getUsage() {
    return Arith.mul(Arith.div(used, total, 4), 100);
  }
}
