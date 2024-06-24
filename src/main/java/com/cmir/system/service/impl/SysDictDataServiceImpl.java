/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.service.impl;

import com.cmir.common.core.domain.entity.SysDictData;
import com.cmir.common.utils.DictUtils;
import com.cmir.system.mapper.SysDictDataMapper;
import com.cmir.system.service.ISysDictDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典 业务层处理
 *
 * @author galudisu
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
  @Autowired private SysDictDataMapper dictDataMapper;

  /**
   * 根据条件分页查询字典数据
   *
   * @param dictData 字典数据信息
   * @return 字典数据集合信息
   */
  @Override
  public List<SysDictData> selectDictDataList(SysDictData dictData) {
    return dictDataMapper.selectDictDataList(dictData);
  }

  /**
   * 根据字典类型和字典键值查询字典数据信息
   *
   * @param dictType 字典类型
   * @param dictValue 字典键值
   * @return 字典标签
   */
  @Override
  public String selectDictLabel(String dictType, String dictValue) {
    return dictDataMapper.selectDictLabel(dictType, dictValue);
  }

  /**
   * 根据字典数据ID查询信息
   *
   * @param dictCode 字典数据ID
   * @return 字典数据
   */
  @Override
  public SysDictData selectDictDataById(Long dictCode) {
    return dictDataMapper.selectDictDataById(dictCode);
  }

  /**
   * 批量删除字典数据信息
   *
   * @param dictCodes 需要删除的字典数据ID
   */
  @Override
  public void deleteDictDataByIds(Long[] dictCodes) {
    for (Long dictCode : dictCodes) {
      SysDictData data = selectDictDataById(dictCode);
      dictDataMapper.deleteDictDataById(dictCode);
      List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
      DictUtils.setDictCache(data.getDictType(), dictDatas);
    }
  }

  /**
   * 新增保存字典数据信息
   *
   * @param data 字典数据信息
   * @return 结果
   */
  @Override
  public int insertDictData(SysDictData data) {
    int row = dictDataMapper.insertDictData(data);
    if (row > 0) {
      List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
      DictUtils.setDictCache(data.getDictType(), dictDatas);
    }
    return row;
  }

  /**
   * 修改保存字典数据信息
   *
   * @param data 字典数据信息
   * @return 结果
   */
  @Override
  public int updateDictData(SysDictData data) {
    int row = dictDataMapper.updateDictData(data);
    if (row > 0) {
      List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
      DictUtils.setDictCache(data.getDictType(), dictDatas);
    }
    return row;
  }
}
