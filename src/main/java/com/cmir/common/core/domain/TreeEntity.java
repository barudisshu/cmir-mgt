/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree基类
 *
 * @author galudisu
 */
public class TreeEntity extends BaseEntity {

  /** 父菜单名称 */
  private String parentName;

  /** 父菜单ID */
  private Long parentId;

  /** 显示顺序 */
  private Integer orderNum;

  /** 祖级列表 */
  private String ancestors;

  /** 子部门 */
  private List<?> children = new ArrayList<>();

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(Integer orderNum) {
    this.orderNum = orderNum;
  }

  public String getAncestors() {
    return ancestors;
  }

  public void setAncestors(String ancestors) {
    this.ancestors = ancestors;
  }

  public List<?> getChildren() {
    return children;
  }

  public void setChildren(List<?> children) {
    this.children = children;
  }
}
