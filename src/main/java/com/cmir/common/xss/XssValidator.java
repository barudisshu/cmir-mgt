/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.xss;

import com.cmir.common.utils.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义xss校验注解实现
 *
 * @author galudisu
 */
public class XssValidator implements ConstraintValidator<Xss, String> {
  private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (StringUtils.isBlank(value)) {
      return true;
    }
    return !containsHtml(value);
  }

  public static boolean containsHtml(String value) {
    StringBuilder sHtml = new StringBuilder();
    Pattern pattern = Pattern.compile(HTML_PATTERN);
    Matcher matcher = pattern.matcher(value);
    while (matcher.find()) {
      sHtml.append(matcher.group());
    }
    return pattern.matcher(sHtml).matches();
  }
}
