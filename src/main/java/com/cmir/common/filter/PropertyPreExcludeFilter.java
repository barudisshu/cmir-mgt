/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.filter;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

/**
 * 排除JSON敏感属性
 *
 * @author galudisu
 */
public class PropertyPreExcludeFilter extends SimplePropertyPreFilter {
  public PropertyPreExcludeFilter() {}

  public PropertyPreExcludeFilter addExcludes(String... filters) {
    for (int i = 0; i < filters.length; i++) {
      this.getExcludes().add(filters[i]);
    }
    return this;
  }
}
