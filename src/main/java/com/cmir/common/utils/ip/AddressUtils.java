/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.common.utils.ip;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cmir.common.config.properties.CmirConfig;
import com.cmir.common.constant.Constants;
import com.cmir.common.utils.StringUtils;
import com.cmir.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 *
 * @author galudisu
 */
public class AddressUtils {
  private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

  // IP地址查询
  public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

  // 未知地址
  public static final String UNKNOWN = "XX XX";

  public static String getRealAddressByIP(String ip) {
    // 内网不查询
    if (IpUtils.internalIp(ip)) {
      return "内网IP";
    }
    if (CmirConfig.isAddressEnabled()) {
      try {
        String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
        if (StringUtils.isEmpty(rspStr)) {
          log.error("获取地理位置异常 {}", ip);
          return UNKNOWN;
        }
        JSONObject obj = JSON.parseObject(rspStr);
        String region = obj.getString("pro");
        String city = obj.getString("city");
        return String.format("%s %s", region, city);
      } catch (Exception e) {
        log.error("获取地理位置异常 {}", ip);
      }
    }
    return UNKNOWN;
  }
}
