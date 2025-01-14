/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.framework.config;

import com.cmir.framework.config.properties.PermitAllUrlProperties;
import com.cmir.framework.security.filter.JwtAuthenticationTokenFilter;
import com.cmir.framework.security.handle.AuthenticationEntryPointImpl;
import com.cmir.framework.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * spring security配置
 *
 * @author galudisu
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  /** 自定义用户认证逻辑 */
  @Autowired private UserDetailsService userDetailsService;

  /** 认证失败处理类 */
  @Autowired private AuthenticationEntryPointImpl unauthorizedHandler;

  /** 退出处理类 */
  @Autowired private LogoutSuccessHandlerImpl logoutSuccessHandler;

  /** token认证过滤器 */
  @Autowired private JwtAuthenticationTokenFilter authenticationTokenFilter;

  /** 跨域过滤器 */
  @Autowired private CorsFilter corsFilter;

  /** 允许匿名访问的地址 */
  @Autowired private PermitAllUrlProperties permitAllUrl;

  /**
   * 解决 无法直接注入 AuthenticationManager
   *
   * @return
   * @throws Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   *
   * <li>anyRequest | 匹配所有请求路径
   * <li>access | SpringEl表达式结果为true时可以访问
   * <li>anonymous | 匿名可以访问
   * <li>denyAll | 用户不能访问
   * <li>fullyAuthenticated | 用户完全认证可以访问（非remember-me下自动登录）
   * <li>hasAnyAuthority | 如果有参数，参数表示权限，则其中任何一个权限可以访问
   * <li>hasAnyRole | 如果有参数，参数表示角色，则其中任何一个角色可以访问
   * <li>hasAuthority | 如果有参数，参数表示权限，则其权限可以访问
   * <li>hasIpAddress | 如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
   * <li>hasRole | 如果有参数，参数表示角色，则其角色可以访问
   * <li>permitAll | 用户可以任意访问
   * <li>rememberMe | 允许通过remember-me登录的用户访问
   * <li>authenticated | 用户登录后可访问
   */
  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    // 注解标记允许匿名访问的url
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
        httpSecurity.authorizeRequests();
    permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

    httpSecurity
      // CSRF禁用，因为不使用session
      .csrf().disable()
      // 禁用HTTP响应标头
      .headers().cacheControl().disable().and()
      // 认证失败处理类
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      // 基于token，所以不需要session
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      // 过滤请求
      .authorizeRequests()
      // 对于登录login 注册register 验证码captchaImage 允许匿名访问
      .antMatchers("/login", "/register", "/captchaImage").permitAll()
      // 静态资源，可匿名访问
      .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
      .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
      // 除上面外的所有请求全部需要鉴权认证
      .anyRequest().authenticated()
      .and()
      .headers().frameOptions().disable();
    // 添加Logout filter
    httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
    // 添加JWT filter
    httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    // 添加CORS filter
    httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
    httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
  }

  /** 强散列哈希加密实现 */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /** 身份认证接口 */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }
}
