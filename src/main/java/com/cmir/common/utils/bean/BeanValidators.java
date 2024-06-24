/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.bean;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * bean对象属性验证
 *
 * @author galudisu
 */
public class BeanValidators {
  public static void validateWithException(Validator validator, Object object, Class<?>... groups)
      throws ConstraintViolationException {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }
}
