package com.phoenix.api.config;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.repositories.auth.UserStatusRepository;
import com.phoenix.auth.JwtProvider;
import com.phoenix.auth.imp.DefaultJwtProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {

    private final UserStatusRepository userStatusRepository;

    public ApplicationConfiguration(
            @Qualifier("UserStatusRepository") UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Bean(value = BeanIds.ALL_USER_STATUS)
    public List<UserStatusEntity> getAllUserStatus() {
        return userStatusRepository.findAll();
    }

    @Bean(value = BeanIds.JWT_PROVIDER)
    public JwtProvider getJwtProvider() {
        return new DefaultJwtProvider();
    }

}
