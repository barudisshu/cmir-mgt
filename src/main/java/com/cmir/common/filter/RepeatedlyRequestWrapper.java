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

import com.cmir.common.constant.Constants;
import com.cmir.common.utils.http.HttpHelper;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 构建可重复读取inputStream的request
 *
 * @author galudisu
 */
public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper {
  private final byte[] body;

  public RepeatedlyRequestWrapper(HttpServletRequest request, ServletResponse response)
      throws IOException {
    super(request);
    request.setCharacterEncoding(Constants.UTF8);
    response.setCharacterEncoding(Constants.UTF8);

    body = HttpHelper.getBodyString(request).getBytes(Constants.UTF8);
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream bais = new ByteArrayInputStream(body);
    return new ServletInputStream() {
      @Override
      public int read() throws IOException {
        return bais.read();
      }

      @Override
      public int available() throws IOException {
        return body.length;
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {}
    };
  }
}
