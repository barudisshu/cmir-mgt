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

import com.cmir.common.core.text.Convert;
import com.cmir.common.utils.ServletUtils;

/**
 * 表格数据处理
 *
 * @author galudisu
 */
public class TableSupport {
  /** 当前记录起始索引 */
  public static final String PAGE_NUM = "pageNum";

  /** 每页显示记录数 */
  public static final String PAGE_SIZE = "pageSize";

  /** 排序列 */
  public static final String ORDER_BY_COLUMN = "orderByColumn";

  /** 排序的方向 "desc" 或者 "asc". */
  public static final String IS_ASC = "isAsc";

  /** 分页参数合理化 */
  public static final String REASONABLE = "reasonable";

  /** 封装分页对象 */
  public static PageDomain getPageDomain() {
    PageDomain pageDomain = new PageDomain();
    pageDomain.setPageNum(Convert.toInt(ServletUtils.getParameter(PAGE_NUM), 1));
    pageDomain.setPageSize(Convert.toInt(ServletUtils.getParameter(PAGE_SIZE), 10));
    pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
    pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
    pageDomain.setReasonable(ServletUtils.getParameterToBool(REASONABLE));
    return pageDomain;
  }

  public static PageDomain buildPageRequest() {
    return getPageDomain();
  }
}
