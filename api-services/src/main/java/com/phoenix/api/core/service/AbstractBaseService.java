package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.exception.ServiceException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AbstractBaseService implements BaseService {
    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractBaseService(List<ExceptionEntity> exceptionEntities) {
        this.exceptionEntities = exceptionEntities;
    }

    public ServiceException getServiceException(String code) {
        ExceptionEntity exceptionEntity = findExceptionByCode(code);

        return new ServiceException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                HttpStatus.valueOf(exceptionEntity.getHttpCode()));
    }

    public ExceptionEntity findExceptionByCode(String code) {
        return exceptionEntities
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }
}
