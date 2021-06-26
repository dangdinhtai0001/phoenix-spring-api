package com.phoenix.api.config;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.auth.UserStatusRepository;
import com.phoenix.api.repositories.common.ExceptionRepository;
import com.phoenix.auth.JwtProvider;
import com.phoenix.auth.imp.DefaultJwtProvider;
import com.phoenix.text.HashingText;
import com.phoenix.util.UUIDFactory;
import com.phoenix.util.imp.ConcurrentUUIDFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * Nơi nạp các bean cần thiết cho ứng dụng
 */
@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    private final UserStatusRepository userStatusRepository;
    private final ExceptionRepository exceptionRepository;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.USER_STATUS_REPOSITORY) UserStatusRepository userStatusRepository,
            @Qualifier(BeanIds.EXCEPTION_REPOSITORY) ExceptionRepository exceptionRepository
    ) {
        this.userStatusRepository = userStatusRepository;
        this.exceptionRepository = exceptionRepository;
    }

    /**
     * @return : List các status của user từ database
     */
    @Bean(value = BeanIds.ALL_USER_STATUS)
    public List<UserStatusEntity> getAllUserStatus() {
        return userStatusRepository.findAll();
    }

    /**
     * @return : List thông tin các Exception được định nghĩa trong database trong database
     */
    @Bean(value = BeanIds.ALL_EXCEPTION)
    public List<ExceptionEntity> getAllException() {
        return exceptionRepository.findAll();
    }

    /**
     * @return : Nạp khóa bí mật của ứng dụng.
     */
    @Bean(value = BeanIds.JWT_SECRET_KEY)
    public String getSecretKey() {
        return HashingText.hashingSha256(this.secret);
    }

    /**
     * @param secretKey : khóa bí mật của ứng dụng
     * @return : Đối tuwojng dùng để generate + validate token (dùng trong quá trình xác thực người dùng)
     */
    @Bean(value = BeanIds.JWT_PROVIDER)
    @DependsOn(BeanIds.JWT_SECRET_KEY)
    public JwtProvider getJwtProvider(@Qualifier(BeanIds.JWT_SECRET_KEY) String secretKey) {
        return new DefaultJwtProvider(secretKey, Long.parseLong(jwtExpired));
    }

    /**
     * @return : Đối tượng sinh mã UUID duy nhất.
     */
    @Bean(value = BeanIds.UUID_Factory)
    public UUIDFactory getUUIDFactory() {
        return new ConcurrentUUIDFactory();
    }
}
