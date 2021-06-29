/*
 * @Author Đặng Đình Tài
 * @Date 6/29/21, 10:04 AM
 */

package com.phoenix.api.services.base;

import com.phoenix.api.component.exception.DefaultHandlerException;
import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractBaseService {
    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractBaseService(List<ExceptionEntity> exceptionEntities) {
        this.exceptionEntities = exceptionEntities;
    }

    protected DefaultHandlerException getDefaultException(String code) {
        ExceptionEntity exceptionEntity = findExceptionByCode(code);

        return new DefaultHandlerException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                HttpStatus.valueOf(exceptionEntity.getHttpCode()));
    }

    private ExceptionEntity findExceptionByCode(String code) {
        return exceptionEntities
                .stream()
                .filter(
                        exceptionEntity -> code.equals(exceptionEntity.getCode())
                )
                .findFirst().orElse(null);
    }
}
