/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.service;

import com.cmir.common.core.domain.entity.SysDictData;
import java.util.List;

/**
 * 字典 业务层
 *
 * @author galudisu
 */
public interface ISysDictDataService {
  /**
   * 根据条件分页查询字典数据
   *
   * @param dictData 字典数据信息
   * @return 字典数据集合信息
   */
  public List<SysDictData> selectDictDataList(SysDictData dictData);

  /**
   * 根据字典类型和字典键值查询字典数据信息
   *
   * @param dictType 字典类型
   * @param dictValue 字典键值
   * @return 字典标签
   */
  public String selectDictLabel(String dictType, String dictValue);

  /**
   * 根据字典数据ID查询信息
   *
   * @param dictCode 字典数据ID
   * @return 字典数据
   */
  public SysDictData selectDictDataById(Long dictCode);

  /**
   * 批量删除字典数据信息
   *
   * @param dictCodes 需要删除的字典数据ID
   */
  public void deleteDictDataByIds(Long[] dictCodes);

  /**
   * 新增保存字典数据信息
   *
   * @param dictData 字典数据信息
   * @return 结果
   */
  public int insertDictData(SysDictData dictData);

  /**
   * 修改保存字典数据信息
   *
   * @param dictData 字典数据信息
   * @return 结果
   */
  public int updateDictData(SysDictData dictData);
}
