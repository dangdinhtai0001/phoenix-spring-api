/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 3:36 PM
 */

package com.phoenix.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableJdbcHttpSession
public class SessionConfiguration {
}
