package com.phoenix.api.config;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.repositories.auth.UserStatusRepository;
import com.phoenix.auth.JwtProvider;
import com.phoenix.auth.imp.DefaultJwtProvider;
import com.phoenix.text.HashingText;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;

@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    private final UserStatusRepository userStatusRepository;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.USER_STATUS_REPOSITORY) UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Bean(value = BeanIds.ALL_USER_STATUS)
    public List<UserStatusEntity> getAllUserStatus() {
        return userStatusRepository.findAll();
    }

    @Bean(value = BeanIds.JWT_SECRET_KEY)
    public String getSecretKey() {
        return HashingText.hashingSha256(this.secret);
    }

    @Bean(value = BeanIds.JWT_PROVIDER)
    @DependsOn(BeanIds.JWT_SECRET_KEY)
    public JwtProvider getJwtProvider(@Qualifier(BeanIds.JWT_SECRET_KEY) String secretKey) {
        return new DefaultJwtProvider(secretKey, Long.parseLong(jwtExpired));
    }
}
