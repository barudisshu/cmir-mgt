/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.core.domain.entity;

import com.cmir.common.annotation.Excel;
import com.cmir.common.annotation.Excel.ColumnType;
import com.cmir.common.core.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 字典类型表 sys_dict_type
 *
 * @author galudisu
 */
public class SysDictType extends BaseEntity {

  /** 字典主键 */
  @Excel(name = "字典主键", cellType = ColumnType.NUMERIC)
  private Long dictId;

  /** 字典名称 */
  @Excel(name = "字典名称")
  private String dictName;

  /** 字典类型 */
  @Excel(name = "字典类型")
  private String dictType;

  /** 状态（0正常 1停用） */
  @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
  private String status;

  public Long getDictId() {
    return dictId;
  }

  public void setDictId(Long dictId) {
    this.dictId = dictId;
  }

  @NotBlank(message = "字典名称不能为空")
  @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
  public String getDictName() {
    return dictName;
  }

  public void setDictName(String dictName) {
    this.dictName = dictName;
  }

  @NotBlank(message = "字典类型不能为空")
  @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
  @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
  public String getDictType() {
    return dictType;
  }

  public void setDictType(String dictType) {
    this.dictType = dictType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("dictId", getDictId())
        .append("dictName", getDictName())
        .append("dictType", getDictType())
        .append("status", getStatus())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .append("remark", getRemark())
        .toString();
  }
}
