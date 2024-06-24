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

import com.cmir.common.core.domain.entity.SysDept;
import com.cmir.common.core.domain.entity.SysMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 *
 * @author galudisu
 */
public class TreeSelect implements Serializable {

  /** 节点ID */
  private Long id;

  /** 节点名称 */
  private String label;

  /** 子节点 */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<TreeSelect> children;

  public TreeSelect() {}

  public TreeSelect(SysDept dept) {
    this.id = dept.getDeptId();
    this.label = dept.getDeptName();
    this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
  }

  public TreeSelect(SysMenu menu) {
    this.id = menu.getMenuId();
    this.label = menu.getMenuName();
    this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<TreeSelect> getChildren() {
    return children;
  }

  public void setChildren(List<TreeSelect> children) {
    this.children = children;
  }
}
