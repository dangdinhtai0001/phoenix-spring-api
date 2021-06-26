/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 11:41 AM
 */

package com.phoenix.api.services.base;

import com.phoenix.api.component.exception.DefaultHandlerException;
import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class AbstractService {

    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractService(List<ExceptionEntity> exceptionEntities) {
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
