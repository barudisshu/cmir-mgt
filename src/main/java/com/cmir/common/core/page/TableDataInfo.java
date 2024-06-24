/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author galudisu
 */
public class TableDataInfo implements Serializable {

  /** 总记录数 */
  private long total;

  /** 列表数据 */
  private List<?> rows;

  /** 消息状态码 */
  private int code;

  /** 消息内容 */
  private String msg;

  /** 表格数据对象 */
  public TableDataInfo() {}

  /**
   * 分页
   *
   * @param list 列表数据
   * @param total 总记录数
   */
  public TableDataInfo(List<?> list, int total) {
    this.rows = list;
    this.total = total;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<?> getRows() {
    return rows;
  }

  public void setRows(List<?> rows) {
    this.rows = rows;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
