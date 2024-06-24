/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.enums;

/**
 * 业务操作类型
 *
 * @author galudisu
 */
public enum BusinessType {
  /** 其它 */
  OTHER,

  /** 新增 */
  INSERT,

  /** 修改 */
  UPDATE,

  /** 删除 */
  DELETE,

  /** 授权 */
  GRANT,

  /** 导出 */
  EXPORT,

  /** 导入 */
  IMPORT,

  /** 强退 */
  FORCE,

  /** 生成代码 */
  GENCODE,

  /** 清空数据 */
  CLEAN,
}
