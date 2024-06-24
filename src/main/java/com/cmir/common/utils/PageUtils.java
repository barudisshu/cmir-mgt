/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils;

import com.cmir.common.core.page.PageDomain;
import com.cmir.common.core.page.TableSupport;
import com.cmir.common.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具类
 *
 * @author galudisu
 */
public class PageUtils extends PageHelper {
  /** 设置请求分页数据 */
  public static void startPage() {
    PageDomain pageDomain = TableSupport.buildPageRequest();
    Integer pageNum = pageDomain.getPageNum();
    Integer pageSize = pageDomain.getPageSize();
    String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
    Boolean reasonable = pageDomain.getReasonable();
    PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
  }

  /** 清理分页的线程变量 */
  public static void clearPage() {
    PageHelper.clearPage();
  }
}
