/*
 * Copyright (c) 2024. Galudisu@gmail.com
 *
 * All rights reserved.
 */

package com.cmir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author galudisu
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
