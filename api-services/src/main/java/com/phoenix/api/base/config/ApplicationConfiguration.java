package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.imp.ExceptionRepositoryImp;
import com.phoenix.common.auth.JwtProvider;
import com.phoenix.common.auth.imp.DefaultJwtProvider;
import com.phoenix.common.text.HashingText;
import com.phoenix.common.util.UUIDFactory;
import com.phoenix.common.util.imp.ConcurrentUUIDFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    private final ExceptionRepositoryImp exceptionRepositoryImp;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP) ExceptionRepositoryImp exceptionRepositoryImp
    ) {
        this.exceptionRepositoryImp = exceptionRepositoryImp;
    }

    /**
     * @return : List thông tin các Exception được định nghĩa trong database
     */
    @Bean(value = BeanIds.ALL_EXCEPTION)
    public List<ExceptionEntity> getAllException() {
        return exceptionRepositoryImp.findAll();
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
