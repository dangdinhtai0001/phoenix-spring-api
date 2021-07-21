package com.phoenix.api.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration(value = "PersistenceConfiguration")
@EnableJpaAuditing
@ComponentScan({"com.phoenix.api.base.*"})
public class PersistenceConfiguration {
}
