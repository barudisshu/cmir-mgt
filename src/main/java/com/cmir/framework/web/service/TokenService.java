/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.web.service;

import com.cmir.common.constant.CacheConstants;
import com.cmir.common.constant.Constants;
import com.cmir.common.core.domain.model.LoginUser;
import com.cmir.common.core.redis.RedisCache;
import com.cmir.common.utils.ServletUtils;
import com.cmir.common.utils.StringUtils;
import com.cmir.common.utils.ip.AddressUtils;
import com.cmir.common.utils.ip.IpUtils;
import com.cmir.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token验证处理
 *
 * @author galudisu
 */
@Component
public class TokenService {
  private static final Logger log = LoggerFactory.getLogger(TokenService.class);

  // 令牌自定义标识
  @Value("${token.header}")
  private String header;

  // 令牌秘钥
  @Value("${token.secret}")
  private String secret;

  // 令牌有效期（默认30分钟）
  @Value("${token.expireTime}")
  private int expireTime;

  protected static final long MILLIS_SECOND = 1000;

  protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

  private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

  @Autowired private RedisCache redisCache;

  /**
   * 获取用户身份信息
   *
   * @return 用户信息
   */
  public LoginUser getLoginUser(HttpServletRequest request) {
    // 获取请求携带的令牌
    String token = getToken(request);
    if (StringUtils.isNotEmpty(token)) {
      try {
        Claims claims = parseToken(token);
        // 解析对应的权限以及用户信息
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getTokenKey(uuid);
        LoginUser user = redisCache.getCacheObject(userKey);
        return user;
      } catch (Exception e) {
        log.error("获取用户信息异常'{}'", e.getMessage());
      }
    }
    return null;
  }

  /** 设置用户身份信息 */
  public void setLoginUser(LoginUser loginUser) {
    if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
      refreshToken(loginUser);
    }
  }

  /** 删除用户身份信息 */
  public void delLoginUser(String token) {
    if (StringUtils.isNotEmpty(token)) {
      String userKey = getTokenKey(token);
      redisCache.deleteObject(userKey);
    }
  }

  /**
   * 创建令牌
   *
   * @param loginUser 用户信息
   * @return 令牌
   */
  public String createToken(LoginUser loginUser) {
    String token = IdUtils.fastUUID();
    loginUser.setToken(token);
    setUserAgent(loginUser);
    refreshToken(loginUser);

    Map<String, Object> claims = new HashMap<>();
    claims.put(Constants.LOGIN_USER_KEY, token);
    return createToken(claims);
  }

  /**
   * 验证令牌有效期，相差不足20分钟，自动刷新缓存
   *
   * @param loginUser
   * @return 令牌
   */
  public void verifyToken(LoginUser loginUser) {
    long expireTime = loginUser.getExpireTime();
    long currentTime = System.currentTimeMillis();
    if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
      refreshToken(loginUser);
    }
  }

  /**
   * 刷新令牌有效期
   *
   * @param loginUser 登录信息
   */
  public void refreshToken(LoginUser loginUser) {
    loginUser.setLoginTime(System.currentTimeMillis());
    loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
    // 根据uuid将loginUser缓存
    String userKey = getTokenKey(loginUser.getToken());
    redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
  }

  /**
   * 设置用户代理信息
   *
   * @param loginUser 登录信息
   */
  public void setUserAgent(LoginUser loginUser) {
    UserAgent userAgent =
        UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
    String ip = IpUtils.getIpAddr();
    loginUser.setIpaddr(ip);
    loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
    loginUser.setBrowser(userAgent.getBrowser().getName());
    loginUser.setOs(userAgent.getOperatingSystem().getName());
  }

  /**
   * 从数据声明生成令牌
   *
   * @param claims 数据声明
   * @return 令牌
   */
  private String createToken(Map<String, Object> claims) {
    return Jwts.builder().claims(claims).signWith(getSigningKey()).compact();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * 从令牌中获取数据声明
   *
   * @param token 令牌
   * @return 数据声明
   */
  private Claims parseToken(String token) {
    return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
  }

  /**
   * 从令牌中获取用户名
   *
   * @param token 令牌
   * @return 用户名
   */
  public String getUsernameFromToken(String token) {
    Claims claims = parseToken(token);
    return claims.getSubject();
  }

  /**
   * 获取请求token
   *
   * @param request
   * @return token
   */
  private String getToken(HttpServletRequest request) {
    String token = request.getHeader(header);
    if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
      token = token.replace(Constants.TOKEN_PREFIX, "");
    }
    return token;
  }

  private String getTokenKey(String uuid) {
    return CacheConstants.LOGIN_TOKEN_KEY + uuid;
  }
}
