/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.http;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用http工具封装
 *
 * @author galudisu
 */
public class HttpHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

  public static String getBodyString(ServletRequest request) {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = null;
    try (InputStream inputStream = request.getInputStream()) {
      reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      String line = "";
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      LOGGER.warn("getBodyString出现问题！");
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          LOGGER.error(ExceptionUtils.getMessage(e));
        }
      }
    }
    return sb.toString();
  }
}
