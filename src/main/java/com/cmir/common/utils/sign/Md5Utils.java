/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.sign;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Md5加密方法
 *
 * @author galudisu
 */
public class Md5Utils {
  private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

  private static byte[] md5(String s) {
    MessageDigest algorithm;
    try {
      algorithm = MessageDigest.getInstance("MD5");
      algorithm.reset();
      algorithm.update(s.getBytes("UTF-8"));
      byte[] messageDigest = algorithm.digest();
      return messageDigest;
    } catch (Exception e) {
      log.error("MD5 Error...", e);
    }
    return null;
  }

  private static final String toHex(byte hash[]) {
    if (hash == null) {
      return null;
    }
    StringBuffer buf = new StringBuffer(hash.length * 2);
    int i;

    for (i = 0; i < hash.length; i++) {
      if ((hash[i] & 0xff) < 0x10) {
        buf.append("0");
      }
      buf.append(Long.toString(hash[i] & 0xff, 16));
    }
    return buf.toString();
  }

  public static String hash(String s) {
    try {
      return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("not supported charset...{}", e);
      return s;
    }
  }
}
