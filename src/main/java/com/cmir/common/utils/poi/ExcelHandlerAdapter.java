/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel数据格式处理适配器
 *
 * @author galudisu
 */
public interface ExcelHandlerAdapter {
  /**
   * 格式化
   *
   * @param value 单元格数据值
   * @param args excel注解args参数组
   * @param cell 单元格对象
   * @param wb 工作簿对象
   * @return 处理后的值
   */
  Object format(Object value, String[] args, Cell cell, Workbook wb);
}
