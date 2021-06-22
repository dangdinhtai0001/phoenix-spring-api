package com.phoenix.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration(value = "PersistenceConfiguration")
@EnableJpaAuditing
public class PersistenceConfiguration {
}
