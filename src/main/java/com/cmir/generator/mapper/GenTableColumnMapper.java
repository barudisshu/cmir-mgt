/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.generator.mapper;

import com.cmir.generator.domain.GenTableColumn;
import java.util.List;

/**
 * 业务字段 数据层
 *
 * @author galudisu
 */
public interface GenTableColumnMapper {
  /**
   * 根据表名称查询列信息
   *
   * @param tableName 表名称
   * @return 列信息
   */
  public List<GenTableColumn> selectDbTableColumnsByName(String tableName);

  /**
   * 查询业务字段列表
   *
   * @param tableId 业务字段编号
   * @return 业务字段集合
   */
  public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

  /**
   * 新增业务字段
   *
   * @param genTableColumn 业务字段信息
   * @return 结果
   */
  public int insertGenTableColumn(GenTableColumn genTableColumn);

  /**
   * 修改业务字段
   *
   * @param genTableColumn 业务字段信息
   * @return 结果
   */
  public int updateGenTableColumn(GenTableColumn genTableColumn);

  /**
   * 删除业务字段
   *
   * @param genTableColumns 列数据
   * @return 结果
   */
  public int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

  /**
   * 批量删除业务字段
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  public int deleteGenTableColumnByIds(Long[] ids);
}
