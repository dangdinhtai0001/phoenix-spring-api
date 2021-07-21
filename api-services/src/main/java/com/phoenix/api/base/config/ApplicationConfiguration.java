package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.ExceptionRepositoryImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {
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
}
