/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.generator.service;

import com.cmir.common.core.text.Convert;
import com.cmir.generator.domain.GenTableColumn;
import com.cmir.generator.mapper.GenTableColumnMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务字段 服务层实现
 *
 * @author galudisu
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {
  @Autowired private GenTableColumnMapper genTableColumnMapper;

  /**
   * 查询业务字段列表
   *
   * @param tableId 业务字段编号
   * @return 业务字段集合
   */
  @Override
  public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
    return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
  }

  /**
   * 新增业务字段
   *
   * @param genTableColumn 业务字段信息
   * @return 结果
   */
  @Override
  public int insertGenTableColumn(GenTableColumn genTableColumn) {
    return genTableColumnMapper.insertGenTableColumn(genTableColumn);
  }

  /**
   * 修改业务字段
   *
   * @param genTableColumn 业务字段信息
   * @return 结果
   */
  @Override
  public int updateGenTableColumn(GenTableColumn genTableColumn) {
    return genTableColumnMapper.updateGenTableColumn(genTableColumn);
  }

  /**
   * 删除业务字段对象
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  @Override
  public int deleteGenTableColumnByIds(String ids) {
    return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
  }
}
