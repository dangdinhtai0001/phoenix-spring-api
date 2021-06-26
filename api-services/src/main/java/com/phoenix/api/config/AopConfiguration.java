/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 10:38 AM
 */

package com.phoenix.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Cấu hình aop
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfiguration {
}
